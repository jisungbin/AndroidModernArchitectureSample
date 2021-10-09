/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserRepositoriesUseCase.kt] created by Ji Sungbin on 21. 10. 9. 오후 6:40
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.domain.usecase

import io.github.jisungbin.sample.domain.repo.GithubUserRepo

class GithubUserRepositoriesUseCase(private val repo: GithubUserRepo) {
    suspend operator fun invoke(loginId: String) = repo.getRepositories(loginId = loginId)
}
