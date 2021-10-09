/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [ResponseToDomainMapper.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:17
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.mapper

import io.github.jisungbin.sample.data.model.infomation.GithubUserInformationResponse
import io.github.jisungbin.sample.data.model.user.GithubUsersResponse
import io.github.jisungbin.sample.domain.model.user.GithubUser

internal fun GithubUsersResponse.toDomain(): List<GithubUser> {
    return items?.map { user ->
        GithubUser(loginId = user.loginId ?: "null", avatarUrl = user.avatarUrl ?: "")
    } ?: emptyList()
}

internal fun GithubUserInformationResponse.toDomain(): List<>