/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserEventsPaginationUseCase.kt] created by Ji Sungbin on 21. 10. 9. 오후 6:41
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.domain.usecase

import io.github.jisungbin.githubusers.domain.repo.GithubUserRepo
import kotlinx.coroutines.CoroutineScope

class GithubUserEventsPaginationUseCase(private val repo: GithubUserRepo) {
    suspend operator fun invoke(
        scope: CoroutineScope,
        loginId: String,
        perPage: Int,
        maxSize: Int
    ) = repo.getEventsPagination(
        scope = scope,
        loginId = loginId,
        perPage = perPage,
        maxSize = maxSize
    )
}
