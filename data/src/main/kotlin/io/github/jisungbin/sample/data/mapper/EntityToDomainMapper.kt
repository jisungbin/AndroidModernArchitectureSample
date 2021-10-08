/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [EntityToDomainMapper.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:37
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.mapper

import io.github.jisungbin.sample.data.local.entity.GithubUserEntity
import io.github.jisungbin.sample.domain.model.user.GithubUser

fun List<GithubUserEntity>.toDomain() =
    map { user -> GithubUser(loginId = user.loginId, avatarUrl = user.avatarUrl) }
