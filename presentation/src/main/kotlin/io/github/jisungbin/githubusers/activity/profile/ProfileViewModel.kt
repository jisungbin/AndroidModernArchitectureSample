/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [ProfileViewModel.kt] created by Ji Sungbin on 21. 10. 8. 오전 11:24
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.activity.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jisungbin.githubusers.activity.profile.mvi.MviProfileState
import io.github.jisungbin.githubusers.domain.doWhen
import io.github.jisungbin.githubusers.domain.usecase.GithubUserEventsPaginationUseCase
import io.github.jisungbin.githubusers.domain.usecase.GithubUserInformationUseCase
import io.github.jisungbin.githubusers.domain.usecase.GithubUserRepositoriesUseCase
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val githubUserInformationUseCase: GithubUserInformationUseCase,
    private val githubUserRepositoriesUseCase: GithubUserRepositoriesUseCase,
    private val githubUserEventsPaginationUseCase: GithubUserEventsPaginationUseCase
) : ContainerHost<MviProfileState, Unit>, ViewModel() {

    private val defaultPageSize = 30
    private val defaultMaxSize = 100

    private val repositoriesPage = 1
    private val repositoriesPerPage = 3

    override val container = container<MviProfileState, Unit>(MviProfileState())

    fun getUserInformation(loginId: String) = intent {
        githubUserInformationUseCase(loginId = loginId).collect { userInformationResult ->
            userInformationResult.doWhen(
                onSuccess = { userInformation ->
                    reduce {
                        state.copy(
                            loaded = true,
                            exception = null,
                            userInformation = userInformation,
                            userRepositories = null
                        )
                    }
                },
                onFail = { exception ->
                    reduce {
                        state.copy(loaded = true, exception = exception)
                    }
                }
            )
        }
    }

    fun getUserRepositories(loginId: String) = intent {
        githubUserRepositoriesUseCase(
            loginId = loginId,
            page = repositoriesPage,
            perPage = repositoriesPerPage
        ).collect { userRepositoriesResult ->
            userRepositoriesResult.doWhen(
                onSuccess = { userRepositories ->
                    reduce {
                        state.copy(
                            loaded = true,
                            exception = null,
                            userInformation = null,
                            userRepositories = userRepositories
                        )
                    }
                },
                onFail = { exception ->
                    reduce {
                        state.copy(loaded = true, exception = exception)
                    }
                }
            )
        }
    }

    suspend fun getUserEventsPagination(loginId: String) = githubUserEventsPaginationUseCase(
        scope = viewModelScope,
        loginId = loginId,
        perPage = defaultPageSize,
        maxSize = defaultMaxSize
    )
}
