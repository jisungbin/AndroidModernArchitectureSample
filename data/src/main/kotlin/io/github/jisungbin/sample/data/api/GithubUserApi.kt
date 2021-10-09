/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserApi.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:05
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.api

import io.github.jisungbin.sample.data.model.event.GithubUserEventItem
import io.github.jisungbin.sample.data.model.infomation.GithubUserInformationResponse
import io.github.jisungbin.sample.data.model.repository.GithubUserRepositoryItem
import io.github.jisungbin.sample.data.model.user.GithubUsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUserApi {
    @GET("/search/users")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<GithubUsersResponse>

    @GET("/users/{userId}/events")
    suspend fun getEvents(
        @Path("userId") loginId: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<List<GithubUserEventItem>>

    @GET("/users/{userId}")
    suspend fun getInformation(
        @Path("userId") loginId: String
    ): Response<GithubUserInformationResponse>

    @GET("/users/{userId}/repos")
    suspend fun getRepositories(
        @Path("userId") loginId: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 3,
        @Query("sort") sort: String = "updated",
    ): Response<List<GithubUserRepositoryItem>>
}
