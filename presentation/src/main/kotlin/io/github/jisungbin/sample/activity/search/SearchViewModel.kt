/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [SearchViewModel.kt] created by Ji Sungbin on 21. 10. 8. 오전 11:24
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.activity.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jisungbin.sample.activity.search.mvi.MviUserSearchState
import io.github.jisungbin.sample.domain.doWhen
import io.github.jisungbin.sample.domain.usecase.GithubUserSearchUseCase
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val githubUserSearchUseCase: GithubUserSearchUseCase
) : ContainerHost<MviUserSearchState, Unit>, ViewModel() {
    override val container = container<MviUserSearchState, Unit>(MviUserSearchState())

    fun search(query: String, page: Int) = intent {
        githubUserSearchUseCase(query, page).collect { userSearchResult ->
            userSearchResult.doWhen(
                onSuccess = { users ->
                    reduce {
                        state.copy(loaded = true, users = users)
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
}
