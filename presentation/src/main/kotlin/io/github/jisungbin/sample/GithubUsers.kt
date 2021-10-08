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
import dagger.hilt.android.HiltAndroidApp

// import io.github.jisungbin.erratum.Erratum

@HiltAndroidApp
class GithubUsers : Application() {
    override fun onCreate() {
        super.onCreate()
        // Erratum.setup(this)
    }
}
