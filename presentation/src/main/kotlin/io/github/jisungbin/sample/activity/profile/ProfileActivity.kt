/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [ProfileActivity.kt] created by Ji Sungbin on 21. 10. 8. 오전 11:24
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.activity.profile

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ProfileActivity : ComponentActivity() {
    private val vm: ProfileViewModel by viewModels()
}
