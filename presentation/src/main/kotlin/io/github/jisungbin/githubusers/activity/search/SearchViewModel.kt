/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [SearchViewModel.kt] created by Ji Sungbin on 21. 10. 8. 오전 11:24
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.activity.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jisungbin.githubusers.domain.usecase.GithubUserSearchPaginationUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val githubUserSearchPaginationUseCase: GithubUserSearchPaginationUseCase
) : ViewModel() {
    private val defaultPageSize = 30
    private val defaultMaxSize = 100

    suspend fun searchPagination(query: String) = githubUserSearchPaginationUseCase(
        query = query,
        scope = viewModelScope,
        perPage = defaultPageSize,
        maxSize = defaultMaxSize
    )
}
