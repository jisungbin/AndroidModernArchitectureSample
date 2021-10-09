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
import io.github.jisungbin.sample.data.local.entity.GithubUserEventEntity
import io.github.jisungbin.sample.data.local.entity.GithubUserInformationEntity
import io.github.jisungbin.sample.data.local.entity.GithubUserRepositoryEntity
import io.github.jisungbin.sample.data.model.event.GithubUserEventItem
import io.github.jisungbin.sample.data.model.infomation.GithubUserInformationResponse
import io.github.jisungbin.sample.data.model.repository.GithubUserRepositoryItem
import io.github.jisungbin.sample.data.model.user.GithubUsersResponse
import io.github.jisungbin.sample.data.util.ISO8601Util

internal fun GithubUsersResponse.toEntity(searchKeyword: String): List<GithubUserEntity> {
    return items?.map { user ->
        GithubUserEntity(
            loginId = user.login ?: "null",
            avatarUrl = user.avatarUrl ?: "",
            searchKeyword = searchKeyword
        )
    } ?: emptyList()
}

internal fun GithubUserInformationResponse.toEntity() = GithubUserInformationEntity(
    bio = bio ?: "",
    loginId = login ?: "null",
    avatarUrl = avatarUrl ?: ""
)

@JvmName("toEntityGithubUserRepositoryItem")
internal fun List<GithubUserRepositoryItem>.toEntity(): List<GithubUserRepositoryEntity> {
    return map { userRepository ->
        GithubUserRepositoryEntity(
            ownerLoginId = userRepository.owner?.login ?: "null",
            name = userRepository.name ?: "null",
            description = userRepository.description ?: "",
            language = userRepository.language ?: "",
            stargazersCount = userRepository.stargazersCount ?: 0
        )
    }
}

@JvmName("toEntityGithubUserEventItem")
internal fun List<GithubUserEventItem>.toEntity(): List<GithubUserEventEntity> {
    return map { userEvent ->
        GithubUserEventEntity(
            avatarUrl = userEvent.actor?.avatarUrl ?: "",
            loginId = userEvent.actor?.login ?: "",
            type = userEvent.type ?: "null",
            createdAt = ISO8601Util.convertRealTime(userEvent.createdAt) ?: "",
            repoPatch = userEvent.repo?.name ?: ""
        )
    }
}
