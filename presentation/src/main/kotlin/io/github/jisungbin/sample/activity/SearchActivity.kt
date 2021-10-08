/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [SearchActivity.kt] created by Ji Sungbin on 21. 10. 8. 오전 10:42
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import io.github.jisungbin.sample.theme.MaterialTheme
import io.github.jisungbin.sample.theme.SystemUiController

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SystemUiController(window).setSystemBarsColor(Color.White)
        setContent {
            MaterialTheme {
                Text("Bye, world!")
            }
        }
    }
}
