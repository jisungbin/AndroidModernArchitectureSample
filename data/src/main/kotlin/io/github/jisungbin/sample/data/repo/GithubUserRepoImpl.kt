/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserRepoImpl.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:11
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.repo

import android.content.Context
import androidx.annotation.IntRange
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import io.github.jisungbin.sample.data.api.GithubUserApi
import io.github.jisungbin.sample.data.local.GithubUserDatabase
import io.github.jisungbin.sample.data.local.entity.GithubUserEntity
import io.github.jisungbin.sample.data.mapper.toDomain
import io.github.jisungbin.sample.data.mapper.toEntity
import io.github.jisungbin.sample.data.paging.UserSearchPagingSource
import io.github.jisungbin.sample.data.util.NetworkUtil
import io.github.jisungbin.sample.data.util.isValid
import io.github.jisungbin.sample.data.util.toFailResult
import io.github.jisungbin.sample.domain.GithubResult
import io.github.jisungbin.sample.domain.repo.GithubUserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Retrofit

class GithubUserRepoImpl(private val context: Context, retrofit: Retrofit) : GithubUserRepo {
    private val db = GithubUserDatabase.build(context)
    private val addedUsers = mutableListOf<GithubUserEntity>()
    private val api = retrofit.create(GithubUserApi::class.java)
    private val networkAvailable get() = NetworkUtil.isNetworkAvailable(context)

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun search(
        query: String,
        @IntRange(from = 1) page: Int,
        @IntRange(from = 1, to = 101) perPage: Int
    ) = callbackFlow {
        try {
            if (networkAvailable) {
                val request = api.search(query, page, perPage)
                trySend(
                    if (request.isValid()) {
                        saveToDatabase(request.body()!!.toEntity(query))
                        GithubResult.Success(request.body()!!.toDomain())
                    } else {
                        request.toFailResult("search")
                    }
                )
            } else {
                trySend(GithubResult.Success(db.dao.getUsers(query).toDomain()))
            }
        } catch (exception: Exception) {
            trySend(GithubResult.Fail(exception))
        }

        close()
    }

    override suspend fun searchPagination(
        query: String,
        scope: CoroutineScope,
        @IntRange(from = 1, to = 101) perPage: Int,
        @IntRange(from = 100, to = 501) maxSize: Int
    ) = Pager(
        config = PagingConfig(
            pageSize = perPage,
            enablePlaceholders = false,
            maxSize = maxSize
        ),
        pagingSourceFactory = { UserSearchPagingSource(this, query) }
    ).flow.cachedIn(scope)

    private suspend fun saveToDatabase(users: List<GithubUserEntity>) = coroutineScope {
        if (users.isNotEmpty() && !addedUsers.any { addedUser -> addedUser.loginId == users.first().loginId }) {
            addedUsers.addAll(users)
            db.dao.insertUsers(users)
        }
    }
}
