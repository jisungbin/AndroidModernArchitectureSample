/*
 * GitMessengerBot © 2021 지성빈 & 구환. all rights reserved.
 * GitMessengerBot license is under the GPL-3.0.
 *
 * [GithubRepository.kt] created by Ji Sungbin on 21. 8. 28. 오후 11:17
 *
 * Please see: https://github.com/GitMessengerBot/GitMessengerBot-Android/blob/master/LICENSE.
 */

package io.github.jisungbin.githubusers.domain.repo

import androidx.paging.PagingData
import io.github.jisungbin.githubusers.domain.GithubResult
import io.github.jisungbin.githubusers.domain.model.event.GithubUserEventItem
import io.github.jisungbin.githubusers.domain.model.event.GithubUserEvents
import io.github.jisungbin.githubusers.domain.model.information.GithubUserInformation
import io.github.jisungbin.githubusers.domain.model.repository.GithubUserRepositories
import io.github.jisungbin.githubusers.domain.model.user.GithubUserItem
import io.github.jisungbin.githubusers.domain.model.user.GithubUsers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface GithubUserRepo {
    suspend fun search(query: String, page: Int, perPage: Int): Flow<GithubResult<GithubUsers>>

    suspend fun searchPagination(
        scope: CoroutineScope,
        query: String,
        perPage: Int,
        maxSize: Int
    ): Flow<PagingData<GithubUserItem>>

    suspend fun getEvents(
        loginId: String,
        page: Int,
        perPage: Int
    ): Flow<GithubResult<GithubUserEvents>>

    suspend fun getEventsPagination(
        scope: CoroutineScope,
        loginId: String,
        perPage: Int,
        maxSize: Int
    ): Flow<PagingData<GithubUserEventItem>>

    suspend fun getInformation(loginId: String): Flow<GithubResult<GithubUserInformation>>

    suspend fun getRepositories(
        loginId: String,
        page: Int,
        perPage: Int
    ): Flow<GithubResult<GithubUserRepositories>>
}
