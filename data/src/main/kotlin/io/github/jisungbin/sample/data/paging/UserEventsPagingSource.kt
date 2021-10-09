/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [UserEventsPagingSource.kt] created by Ji Sungbin on 21. 10. 9. 오후 5:52
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.jisungbin.sample.domain.doWhen
import io.github.jisungbin.sample.domain.model.event.GithubUserEventItem
import io.github.jisungbin.sample.domain.repo.GithubUserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class UserEventsPagingSource(
    private val repo: GithubUserRepo,
    private val loginId: String
) : PagingSource<Int, GithubUserEventItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubUserEventItem> {
        return try {
            val nextPage = params.key ?: 1
            val perPage = params.loadSize
            var pagingResult: LoadResult<Int, GithubUserEventItem>? = null

            CoroutineScope(Dispatchers.IO).launch {
                repo.getEvents(
                    loginId = loginId,
                    page = nextPage,
                    perPage = perPage
                ).collect { userEventsResult ->
                    userEventsResult.doWhen(
                        onSuccess = { userEvents ->
                            val nextKey =
                                if (userEvents.items.size < perPage) null else nextPage + 1
                            pagingResult = LoadResult.Page(
                                data = userEvents.items,
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

    override fun getRefreshKey(state: PagingState<Int, GithubUserEventItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
