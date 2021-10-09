/*
 * GitMessengerBot © 2021 지성빈 & 구환. all rights reserved.
 * GitMessengerBot license is under the GPL-3.0.
 *
 * [GithubRepository.kt] created by Ji Sungbin on 21. 8. 28. 오후 11:17
 *
 * Please see: https://github.com/GitMessengerBot/GitMessengerBot-Android/blob/master/LICENSE.
 */

package io.github.jisungbin.sample.domain.repo

import androidx.paging.PagingData
import io.github.jisungbin.sample.domain.GithubResult
import io.github.jisungbin.sample.domain.model.event.GithubUserEvents
import io.github.jisungbin.sample.domain.model.information.GithubUserInformation
import io.github.jisungbin.sample.domain.model.repository.GithubUserRepositories
import io.github.jisungbin.sample.domain.model.user.GithubUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface GithubUserRepo {
    suspend fun search(query: String, page: Int, perPage: Int): Flow<GithubResult<List<GithubUser>>>

    suspend fun searchPagination(
        scope: CoroutineScope,
        query: String,
        perPage: Int,
        maxSize: Int
    ): Flow<PagingData<GithubUser>>

    suspend fun getInformation(userId: String): Flow<GithubResult<GithubUserInformation>>

    suspend fun getRepositories(
        userId: String,
        page: Int,
        perPage: Int,
        sort: String,
        direction: String
    ): Flow<GithubResult<GithubUserRepositories>>

    suspend fun getRepositoriesPagination(
        scope: CoroutineScope,
        userId: String,
        page: Int,
        perPage: Int,
        sort: String,
        direction: String
    ): Flow<PagingData<GithubUserRepositories>>

    suspend fun getEvents(
        userId: String,
        page: Int,
        perPage: Int
    ): Flow<GithubResult<GithubUserEvents>>

    suspend fun getEventsPagination(
        scope: CoroutineScope,
        userId: String,
        page: Int,
        perPage: Int
    ): Flow<PagingData<GithubUserEvents>>
}
