/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [ResponseToDomainMapper.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:17
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.data.mapper

import io.github.jisungbin.sample.data.model.event.GithubUserEventItem
import io.github.jisungbin.sample.data.model.infomation.GithubUserInformationResponse
import io.github.jisungbin.sample.data.model.repository.GithubUserRepositoryItem
import io.github.jisungbin.sample.data.model.user.GithubUsersResponse
import io.github.jisungbin.sample.data.util.ISO8601Util
import io.github.jisungbin.sample.domain.model.event.GithubUserEvents
import io.github.jisungbin.sample.domain.model.information.GithubUserInformation
import io.github.jisungbin.sample.domain.model.repository.GithubUserRepositories
import io.github.jisungbin.sample.domain.model.user.GithubUserItem
import io.github.jisungbin.sample.domain.model.user.GithubUsers

private typealias GithubUserRepositoryItemDomain = io.github.jisungbin.sample.domain.model.repository.GithubUserRepositoryItem
private typealias GithubUserEventItemDomain = io.github.jisungbin.sample.domain.model.event.GithubUserEventItem

internal fun GithubUsersResponse.toDomain() = GithubUsers(
    items = items?.map { user ->
        GithubUserItem(loginId = user.login ?: "null", avatarUrl = user.avatarUrl ?: "")
    } ?: emptyList()
)

internal fun GithubUserInformationResponse.toDomain() = GithubUserInformation(
    bio = bio ?: "",
    loginId = login ?: "null",
    avatarUrl = avatarUrl ?: ""
)

@JvmName("toDomainGithubUserRepositoryItem")
internal fun List<GithubUserRepositoryItem>.toDomain(): GithubUserRepositories {
    return GithubUserRepositories(
        items = map { userRepository ->
            GithubUserRepositoryItemDomain(
                ownerLoginId = userRepository.owner?.login ?: "null",
                name = userRepository.name ?: "null",
                description = userRepository.description ?: "",
                language = userRepository.language ?: "",
                stargazersCount = userRepository.stargazersCount ?: 0
            )
        }
    )
}

@JvmName("toDomainGithubUserEventItem")
internal fun List<GithubUserEventItem>.toDomain(): GithubUserEvents {
    return GithubUserEvents(
        items = map { userEvent ->
            GithubUserEventItemDomain(
                avatarUrl = userEvent.actor?.avatarUrl ?: "",
                loginId = userEvent.actor?.login ?: "",
                type = userEvent.type ?: "null",
                createdAt = ISO8601Util.convertRealTime(userEvent.createdAt) ?: "",
                repoPatch = userEvent.repo?.name ?: ""
            )
        }
    )
}
