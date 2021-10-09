/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [GithubUsers.kt] created by Ji Sungbin on 21. 10. 8. 오전 11:21
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

package io.github.jisungbin.sample

import android.app.Application
import android.widget.Toast
import dagger.hilt.android.HiltAndroidApp
import io.github.jisungbin.erratum.Erratum
import io.github.jisungbin.sample.util.extension.toast
import java.util.Calendar

@HiltAndroidApp
class GithubUsers : Application() {
    override fun onCreate() {
        super.onCreate()
        Erratum.setup(this)

        val builtDate = Calendar.getInstance().apply { timeInMillis = BuildConfig.TIMESTAMP }
        val builtTime = "${builtDate.get(Calendar.HOUR_OF_DAY)}h" +
            " ${builtDate.get(Calendar.MINUTE)}m " +
            "${builtDate.get(Calendar.SECOND)}s"

        toast(applicationContext, "Built at: $builtTime", Toast.LENGTH_LONG)
    }
}
