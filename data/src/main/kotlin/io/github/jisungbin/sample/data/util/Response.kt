/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [Response.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:09
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.util

import io.github.jisungbin.sample.domain.GithubResult
import retrofit2.Response

fun <T> Response<T>.isValid() = isSuccessful && body() != null

fun <T> Response<T>.toFailResult(requestMethod: String) = GithubResult.Fail(
    Exception("Github.$requestMethod request fail.\n\n${errorBody()?.use { it.string() }}")
)
