/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [MviUserSearchState.kt] created by Ji Sungbin on 21. 10. 8. 오후 8:24
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.activity.search.mvi

import io.github.jisungbin.sample.domain.model.user.GithubUser
import io.github.jisungbin.sample.mvi.MviBaseState

data class MviUserSearchState(
    override val loaded: Boolean = false,
    override val exception: Exception? = null,
    val users: List<GithubUser> = emptyList()
) : MviBaseState
