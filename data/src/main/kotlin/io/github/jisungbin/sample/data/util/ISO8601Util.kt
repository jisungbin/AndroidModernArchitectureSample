/*
 * GitMessengerBot © 2021 지성빈 & 구환. all rights reserved.
 * GitMessengerBot license is under the GPL-3.0.
 *
 * [ISO8601Util.kt] created by Ji Sungbin on 21. 9. 6. 오후 5:28
 *
 * Please see: https://github.com/GitMessengerBot/GitMessengerBot-Android/blob/master/LICENSE.
 */

package io.github.jisungbin.sample.data.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object ISO8601Util {
    fun convertRealTime(time: String?): String? {
        if (time == null) return null

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("Etc/UTC")
        }

        val outputFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault())
        val date = inputFormat.parse(time) ?: return null

        return outputFormat.format(date).toString()
    }
}
