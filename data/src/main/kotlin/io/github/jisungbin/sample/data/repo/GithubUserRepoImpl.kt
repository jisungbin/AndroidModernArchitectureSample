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
import io.github.jisungbin.sample.data.local.entity.GithubUserEntityMarker
import io.github.jisungbin.sample.data.local.entity.GithubUserEventEntity
import io.github.jisungbin.sample.data.local.entity.GithubUserInformationEntity
import io.github.jisungbin.sample.data.local.entity.GithubUserRepositoryEntity
import io.github.jisungbin.sample.data.mapper.toDomain
import io.github.jisungbin.sample.data.mapper.toEntity
import io.github.jisungbin.sample.data.paging.UserEventsPagingSource
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
    private val addedData = mutableListOf<GithubUserEntityMarker>()
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
                val request = api.search(query = query, page = page, perPage = perPage)
                trySend(
                    if (request.isValid()) {
                        saveUsersToDatabase(request.body()!!.toEntity(query))
                        GithubResult.Success(request.body()!!.toDomain())
                    } else {
                        request.toFailResult("search")
                    }
                )
            } else {
                trySend(GithubResult.Success(db.userDao.loadAllFromSearchKeyword(query).toDomain()))
            }
        } catch (exception: Exception) {
            trySend(GithubResult.Fail(exception))
        }

        close()
    }

    override suspend fun searchPagination(
        scope: CoroutineScope,
        query: String,
        @IntRange(from = 1, to = 101) perPage: Int,
        @IntRange(from = 100, to = 501) maxSize: Int
    ) = Pager(
        config = PagingConfig(
            pageSize = perPage,
            enablePlaceholders = false,
            maxSize = maxSize
        ),
        pagingSourceFactory = { UserSearchPagingSource(this, query) }
    ).flow.cachedIn(scope = scope)

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getEvents(
        loginId: String,
        page: Int,
        perPage: Int
    ) = callbackFlow {
        try {
            if (networkAvailable) {
                val request = api.getEvents(loginId = loginId, page = page, perPage = perPage)
                trySend(
                    if (request.isValid()) {
                        saveUserEventsToDatabase(request.body()!!.toEntity())
                        GithubResult.Success(request.body()!!.toDomain())
                    } else {
                        request.toFailResult("getEvents")
                    }
                )
            } else {
                trySend(
                    GithubResult.Success(
                        db.userEventDao.loadAllFromLoginId(loginId).toDomain()
                    )
                )
            }
        } catch (exception: Exception) {
            trySend(GithubResult.Fail(exception))
        }

        close()
    }

    override suspend fun getEventsPagination(
        scope: CoroutineScope,
        loginId: String,
        @IntRange(from = 1, to = 101) perPage: Int,
        @IntRange(from = 100, to = 501) maxSize: Int
    ) = Pager(
        config = PagingConfig(
            pageSize = perPage,
            enablePlaceholders = false,
            maxSize = maxSize
        ),
        pagingSourceFactory = { UserEventsPagingSource(repo = this, loginId = loginId) }
    ).flow.cachedIn(scope = scope)

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getInformation(loginId: String) = callbackFlow {
        try {
            if (networkAvailable) {
                val request = api.getInformation(loginId = loginId)
                trySend(
                    if (request.isValid()) {
                        saveUserInformationToDatabase(request.body()!!.toEntity())
                        GithubResult.Success(request.body()!!.toDomain())
                    } else {
                        request.toFailResult("getInformation")
                    }
                )
            } else {
                trySend(
                    GithubResult.Success(
                        db.userInformationDao.loadFromLoginId(loginId = loginId).toDomain()
                    )
                )
            }
        } catch (exception: Exception) {
            trySend(GithubResult.Fail(exception))
        }

        close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getRepositories(loginId: String) = callbackFlow {
        try {
            if (networkAvailable) {
                val request = api.getRepositories(loginId = loginId)
                trySend(
                    if (request.isValid()) {
                        saveUserRepositoriesToDatabase(request.body()!!.toEntity())
                        GithubResult.Success(request.body()!!.toDomain())
                    } else {
                        request.toFailResult("getRepositories")
                    }
                )
            } else {
                trySend(
                    GithubResult.Success(
                        db.userRepositoryDao.loadAllFromOwnerLoginId(loginId).toDomain()
                    )
                )
            }
        } catch (exception: Exception) {
            trySend(GithubResult.Fail(exception))
        }

        close()
    }

    private suspend fun saveUsersToDatabase(users: List<GithubUserEntity>) = coroutineScope {
        if (users.isNotEmpty() && !addedData.any { it.loginId == users.first().loginId }) {
            addedData.addAll(users)
            db.userDao.insertAll(users)
        }
    }

    private suspend fun saveUserEventsToDatabase(userEvents: List<GithubUserEventEntity>) =
        coroutineScope {
            if (userEvents.isNotEmpty() && !addedData.any { it.loginId == userEvents.first().loginId }) {
                addedData.addAll(userEvents)
                db.userEventDao.insertAll(userEvents)
            }
        }

    private suspend fun saveUserInformationToDatabase(userInformation: GithubUserInformationEntity) =
        coroutineScope {
            if (!addedData.any { it.loginId == userInformation.loginId }) {
                addedData.add(userInformation)
                db.userInformationDao.insert(userInformation)
            }
        }

    private suspend fun saveUserRepositoriesToDatabase(userRepositories: List<GithubUserRepositoryEntity>) =
        coroutineScope {
            if (userRepositories.isNotEmpty() && !addedData.any { it.loginId == userRepositories.first().loginId }) {
                addedData.addAll(userRepositories)
                db.userRepositoryDao.insertAll(userRepositories)
            }
        }
}
