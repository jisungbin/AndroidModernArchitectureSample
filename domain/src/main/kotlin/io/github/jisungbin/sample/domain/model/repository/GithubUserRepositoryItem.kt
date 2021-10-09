/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserRepositoryItem.kt] created by Ji Sungbin on 21. 10. 9. 오후 3:50
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.domain.model.repository

data class GithubUserRepositoryItem(
    val name: String,
    val description: String,
    val language: String,
    val stargazersCount: Int
)
