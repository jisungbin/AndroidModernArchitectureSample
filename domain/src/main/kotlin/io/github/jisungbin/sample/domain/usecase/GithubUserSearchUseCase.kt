/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserSerachUsecase.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:48
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.domain.usecase

import androidx.annotation.IntRange
import io.github.jisungbin.sample.domain.repo.GithubUserRepo

class GithubUserSearchUseCase(private val repo: GithubUserRepo) {
    suspend operator fun invoke(query: String, @IntRange(from = 1) page: Int) =
        repo.search(query, page)
}
