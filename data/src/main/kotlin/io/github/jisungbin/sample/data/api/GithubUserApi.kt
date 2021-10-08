/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserApi.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:05
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.api

import io.github.jisungbin.sample.data.model.search.GithubSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubUserApi {
    @GET("/search/users")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<GithubSearchResponse>

    /*@GET("/search/users")
    suspend fun getInformation(
        @Query("userId") query: String,
    ): Response<GithubUserInformationResponse>*/
}
