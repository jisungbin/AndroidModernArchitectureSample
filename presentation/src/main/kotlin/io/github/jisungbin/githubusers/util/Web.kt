/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [Web.kt] created by Ji Sungbin on 21. 10. 9. 오후 10:29
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.githubusers.util

import android.content.Context
import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import io.github.jisungbin.githubusers.R
import io.github.jisungbin.githubusers.util.extension.toast

object Web {
    fun open(context: Context, address: String) {
        try {
            val builder = CustomTabsIntent.Builder()
            builder.build()
                .intent
                .addCategory(Intent.CATEGORY_BROWSABLE)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            val customTabIntent = builder.build()
            customTabIntent.launchUrl(context, address.toUri())
        } catch (exception: Exception) {
            exception.printStackTrace()
            toast(context, context.getString(R.string.web_toast_non_install_browser))
        }
    }
}
