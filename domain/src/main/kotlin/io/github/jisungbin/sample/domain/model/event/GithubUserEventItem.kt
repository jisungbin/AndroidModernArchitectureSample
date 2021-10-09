/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUserEventItem.kt] created by Ji Sungbin on 21. 10. 9. 오후 3:54
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.domain.model.event

data class GithubUserEventItem(
    val avatarUrl: String,
    val loginId: String,
    val type: String,
    val createdAt: String,
    val repoName: String,
    val repoUrl: String
)
