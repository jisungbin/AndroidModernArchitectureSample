/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [ProfileViewModel.kt] created by Ji Sungbin on 21. 10. 8. 오전 11:24
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.activity.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jisungbin.sample.activity.profile.mvi.MviProfileActivityState
import io.github.jisungbin.sample.domain.doWhen
import io.github.jisungbin.sample.domain.usecase.GithubUserEventsPaginationUseCase
import io.github.jisungbin.sample.domain.usecase.GithubUserInformationUseCase
import io.github.jisungbin.sample.domain.usecase.GithubUserRepositoriesUseCase
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
) : ContainerHost<MviProfileActivityState, Unit>, ViewModel() {

    private val defaultPageSize = 30
    private val defaultMaxSize = 100

    override val container = container<MviProfileActivityState, Unit>(MviProfileActivityState())

    fun getUserInformation(loginId: String) = intent {
        githubUserInformationUseCase(loginId).collect { userInformationResult ->
            userInformationResult.doWhen(
                onSuccess = { userInformation ->
                    reduce {
                        state.copy(
                            loaded = true,
                            exception = null,
                            userInformation = userInformation
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
        githubUserRepositoriesUseCase(loginId).collect { userRepositoriesResult ->
            userRepositoriesResult.doWhen(
                onSuccess = { userRepositories ->
                    reduce {
                        state.copy(
                            loaded = true,
                            exception = null,
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
