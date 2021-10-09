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
import io.github.jisungbin.sample.data.local.entity.GithubUserEventEntity
import io.github.jisungbin.sample.data.local.entity.GithubUserInformationEntity
import io.github.jisungbin.sample.data.local.entity.GithubUserRepositoryEntity
import io.github.jisungbin.sample.domain.model.event.GithubUserEventItem
import io.github.jisungbin.sample.domain.model.event.GithubUserEvents
import io.github.jisungbin.sample.domain.model.information.GithubUserInformation
import io.github.jisungbin.sample.domain.model.repository.GithubUserRepositories
import io.github.jisungbin.sample.domain.model.repository.GithubUserRepositoryItem
import io.github.jisungbin.sample.domain.model.user.GithubUserItem
import io.github.jisungbin.sample.domain.model.user.GithubUsers

@JvmName("toDomainGithubUserEntity")
internal fun List<GithubUserEntity>.toDomain() =
    GithubUsers(
        items = map { user ->
            GithubUserItem(loginId = user.loginId, avatarUrl = user.avatarUrl)
        }.distinctBy { user -> user.loginId }
    )

@JvmName("toDomainGithubUserInformationEntity")
internal fun GithubUserInformationEntity.toDomain() = GithubUserInformation(
    bio = bio,
    loginId = loginId,
    avatarUrl = avatarUrl
)

@JvmName("toDomainGithubUserRepositoryEntity")
internal fun List<GithubUserRepositoryEntity>.toDomain(): GithubUserRepositories {
    return GithubUserRepositories(
        items = map { userRepository ->
            GithubUserRepositoryItem(
                ownerLoginId = userRepository.ownerLoginId,
                name = userRepository.name,
                description = userRepository.description,
                language = userRepository.language,
                stargazersCount = userRepository.stargazersCount
            )
        }.distinctBy { repository -> repository.name }
    )
}

@JvmName("toDomainGithubUserEventEntity")
internal fun List<GithubUserEventEntity>.toDomain(): GithubUserEvents {
    return GithubUserEvents(
        items = map { userEvent ->
            GithubUserEventItem(
                avatarUrl = userEvent.avatarUrl,
                loginId = userEvent.loginId,
                type = userEvent.type,
                createdAt = userEvent.createdAt,
                repoName = userEvent.repoName,
                repoUrl = userEvent.repoUrl
            )
        }.distinctBy { event -> event.createdAt }
    )
}
