/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserSearchPaginationUseCase.kt] created by Ji Sungbin on 21. 10. 8. 오후 4:48
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.domain.usecase

import io.github.jisungbin.sample.domain.repo.GithubUserRepo
import kotlinx.coroutines.CoroutineScope

class GithubUserSearchPaginationUseCase(private val repo: GithubUserRepo) {
    suspend operator fun invoke(
        query: String,
        scope: CoroutineScope,
        perPage: Int,
        maxSize: Int
    ) = repo.searchPagination(
        query = query,
        scope = scope,
        perPage = perPage,
        maxSize = maxSize
    )
}
