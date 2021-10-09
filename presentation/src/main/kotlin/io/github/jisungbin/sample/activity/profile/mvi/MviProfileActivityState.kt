/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [MviProfileActivityState.kt] created by Ji Sungbin on 21. 10. 9. 오후 9:19
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.activity.profile.mvi

import io.github.jisungbin.sample.domain.model.information.GithubUserInformation
import io.github.jisungbin.sample.domain.model.repository.GithubUserRepositories
import io.github.jisungbin.sample.mvi.BaseMviState

data class MviProfileActivityState(
    override val loaded: Boolean = false,
    override val exception: Exception? = null,
    val userInformation: GithubUserInformation = GithubUserInformation(
        bio = "",
        loginId = "",
        avatarUrl = ""
    ),
    val userRepositories: GithubUserRepositories = GithubUserRepositories(emptyList())
) : BaseMviState
