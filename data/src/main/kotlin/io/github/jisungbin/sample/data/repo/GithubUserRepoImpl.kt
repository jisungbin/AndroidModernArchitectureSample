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

private enum class DataType {
    USERS, USER_EVENTS, USER_INFORMATION, USER_REPOSITORIES
}

private data class AddedData(val key: String, val type: DataType)

class GithubUserRepoImpl(private val context: Context, retrofit: Retrofit) : GithubUserRepo {
    private val db = GithubUserDatabase.build(context)
    private val addedData = mutableListOf<AddedData>() // 중복으로 디비 저장 방지
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
        if (users.isNotEmpty()) {
            val data = AddedData(key = users.first().loginId, type = DataType.USERS)
            if (!addedData.contains(data)) {
                addedData.add(data)
                db.userDao.insertAll(users)
            }
        }
    }

    private suspend fun saveUserEventsToDatabase(userEvents: List<GithubUserEventEntity>) =
        coroutineScope {
            if (userEvents.isNotEmpty()) {
                val data =
                    AddedData(key = userEvents.first().createdAt, type = DataType.USER_EVENTS)
                if (!addedData.contains(data)) {
                    addedData.add(data)
                    db.userEventDao.insertAll(userEvents)
                }
            }
        }

    private suspend fun saveUserInformationToDatabase(userInformation: GithubUserInformationEntity) =
        coroutineScope {
            val data = AddedData(key = userInformation.loginId, type = DataType.USER_INFORMATION)
            if (!addedData.contains(data)) {
                addedData.add(data)
                db.userInformationDao.insert(userInformation)
            }
        }

    private suspend fun saveUserRepositoriesToDatabase(userRepositories: List<GithubUserRepositoryEntity>) =
        coroutineScope {
            if (userRepositories.isNotEmpty()) {
                val data = AddedData(
                    key = userRepositories.first().ownerLoginId,
                    type = DataType.USER_REPOSITORIES
                )
                addedData.add(data)
                db.userRepositoryDao.insertAll(userRepositories)
            }
        }
}
