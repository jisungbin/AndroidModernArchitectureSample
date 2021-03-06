/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [BaseMviState.kt] created by Ji Sungbin on 21. 10. 8. 오전 11:05
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.mvi

interface BaseMviState {
    val loaded: Boolean
    val exception: Exception?

    fun isException() = exception != null
}
