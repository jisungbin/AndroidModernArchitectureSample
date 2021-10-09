/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [UserSearchPagingSource.kt] created by Ji Sungbin on 21. 10. 8. 오후 10:51
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.jisungbin.githubusers.domain.doWhen
import io.github.jisungbin.githubusers.domain.model.user.GithubUserItem
import io.github.jisungbin.githubusers.domain.repo.GithubUserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class UserSearchPagingSource(
    private val repo: GithubUserRepo,
    private val query: String
) : PagingSource<Int, GithubUserItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUserItem> {
        return try {
            val nextPage = params.key ?: 1
            val perPage = params.loadSize
            var pagingResult: LoadResult<Int, GithubUserItem>? = null

            CoroutineScope(Dispatchers.IO).launch {
                repo.search(
                    query = query,
                    page = nextPage,
                    perPage = perPage
                ).collect { searchedUserResult ->
                    searchedUserResult.doWhen(
                        onSuccess = { users ->
                            val nextKey = if (users.items.size < perPage) null else nextPage + 1
                            pagingResult = LoadResult.Page(
                                data = users.items,
                                prevKey = if (nextPage == 1) null else nextPage - 1,
                                nextKey = nextKey
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

    override fun getRefreshKey(state: PagingState<Int, GithubUserItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
