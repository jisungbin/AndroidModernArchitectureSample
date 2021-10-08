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
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jisungbin.sample.activity.search.paging.UserSearchPagingSource
import io.github.jisungbin.sample.domain.model.user.GithubUser
import io.github.jisungbin.sample.domain.usecase.GithubUserSearchUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val githubUserSearchUseCase: GithubUserSearchUseCase
) : ViewModel() {
    private val defaultPageSize = 30

    fun searchPagination(query: String): Flow<PagingData<GithubUser>> {
        return Pager(
            config = PagingConfig(
                pageSize = defaultPageSize,
                enablePlaceholders = false,
                maxSize = 100
            ),
            pagingSourceFactory = { UserSearchPagingSource(githubUserSearchUseCase, query) }
        ).flow.cachedIn(viewModelScope)
    }
}
