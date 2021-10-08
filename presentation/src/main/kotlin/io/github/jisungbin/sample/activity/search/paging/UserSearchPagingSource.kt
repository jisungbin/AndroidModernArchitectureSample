/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [UserSearchPagingSource.kt] created by Ji Sungbin on 21. 10. 8. 오후 10:51
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.activity.search.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.jisungbin.sample.domain.doWhen
import io.github.jisungbin.sample.domain.model.user.GithubUser
import io.github.jisungbin.sample.domain.usecase.GithubUserSearchUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserSearchPagingSource(
    private val githubUserSearchUseCase: GithubUserSearchUseCase,
    private val query: String
) : PagingSource<Int, GithubUser>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUser> {
        return try {
            val nextPage = params.key ?: 1
            var pagingResult: LoadResult<Int, GithubUser>? = null

            CoroutineScope(Dispatchers.IO).launch {
                githubUserSearchUseCase(
                    query = query,
                    page = nextPage
                ).collect { searchedUserResult ->
                    searchedUserResult.doWhen(
                        onSuccess = { users ->
                            pagingResult = LoadResult.Page(
                                data = users,
                                prevKey = if (nextPage == 1) null else nextPage - 1,
                                nextKey = nextPage + 1
                            )
                        },
                        onFail = { exception ->
                            pagingResult = LoadResult.Error(exception)
                        }
                    )
                }
            }.join()

            return pagingResult!!
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubUser>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
