/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [MviBaseState.kt] created by Ji Sungbin on 21. 10. 8. 오전 11:05
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.mvi

interface MviBaseState {
    val loaded: Boolean
    val exception: Exception?

    fun isException() = exception != null
}
