/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [Theme.kt] created by Ji Sungbin on 21. 10. 8. 오전 11:10
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import io.github.jisungbin.sample.R

val defaultFontFamily = FontFamily(Font(R.font.nanumbarungothic))

// 100% white theme! ❤️
val colors = lightColors().copy(
    primary = Color.White,
    primaryVariant = Color.White,
    secondary = Color.White
)

private val typography = Typography(defaultFontFamily = defaultFontFamily)

@Composable
fun MaterialTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = colors,
        typography = typography
    ) {
        content()
    }
}
