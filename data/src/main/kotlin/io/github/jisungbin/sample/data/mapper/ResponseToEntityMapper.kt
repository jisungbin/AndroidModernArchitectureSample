/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [ResponseToEntityMapper.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:21
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.mapper

import io.github.jisungbin.sample.data.local.entity.GithubUserEntity
import io.github.jisungbin.sample.data.model.user.GithubUsersResponse

internal fun GithubUsersResponse.toEntity(searchKeyword: String): List<GithubUserEntity> {
    return items?.map { user ->
        GithubUserEntity(
            loginId = user.login ?: "null",
            avatarUrl = user.avatarUrl ?: "",
            searchKeyword = searchKeyword
        )
    } ?: emptyList()
}
