/*
 * AndroidModernArchitectureSample © 2021 Ji Sungbin. all rights reserved.
 * AndroidModernArchitectureSample license is under the Apache-2.0.
 *
 * [Dependencies.kt] created by Ji Sungbin on 21. 10. 8. 오전 10:47
 *
 * Please see: https://github.com/jisungbin/AndroidModernArchitectureSample/blob/master/LICENSE
 */

import org.gradle.api.JavaVersion

object Application {
    const val minSdk = 24
    const val targetSdk = 31
    const val compileSdk = 31
    const val jvmTarget = "11"
    const val versionCode = 1
    const val versionName = "1.0.0"

    val targetCompat = JavaVersion.VERSION_11
    val sourceCompat = JavaVersion.VERSION_11
}

object Versions {
    object Essential {
        const val Kotlin = "1.5.30"
        const val CoreKtx = "1.6.0"
        const val Coroutines = "1.5.1"
        const val Gradle = "7.1.0-alpha05"
    }

    object Ui {
        const val Browser = "1.3.0"
        const val Material = "1.4.0"
    }

    object Util {
        const val Erratum = "1.0.1"
        const val Jackson = "2.13.0"
        const val LeakCanary = "2.7"
        const val CheckDependencyUpdates = "1.5.0"
    }

    object Network {
        const val OkHttp = "4.9.2"
        const val Retrofit = "2.9.0"
    }

    object Jetpack {
        const val Room = "2.4.0-alpha05"
        const val Hilt = "2.39.1"
        const val Paging = "3.0.1"
    }

    object Compose {
        const val Lottie = "4.2.0"
        const val Master = "1.0.3"
        const val Activity = "1.3.1"
        const val SwipeRefresh = "0.19.0"
        const val Paging = "1.0.0-alpha13"
        const val LandscapistCoil = "1.3.7"
        const val Lifecycle = "1.0.0-alpha07"
        const val ConstraintLayout = "1.0.0-beta01"
    }

    object OssLicense {
        const val Master = "17.0.0"
        const val Classpath = "0.10.4"
    }

    object Mvi {
        const val Orbit = "4.2.0"
    }
}

object Dependencies {
    val mvi = "org.orbit-mvi:orbit-viewmodel:${Versions.Mvi.Orbit}"

    const val LandscapistCoil =
        "com.github.skydoves:landscapist-coil:${Versions.Compose.LandscapistCoil}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.Jetpack.Hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Jetpack.Hilt}"

    const val roomCompiler = "androidx.room:room-compiler:${Versions.Jetpack.Room}"

    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.Util.LeakCanary}"

    val essential = listOf(
        "androidx.core:core-ktx:${Versions.Essential.CoreKtx}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Essential.Coroutines}"
    )

    val ui = listOf(
        "androidx.browser:browser:${Versions.Ui.Browser}",
        "com.google.android.material:material:${Versions.Ui.Material}",
        "com.google.android.gms:play-services-oss-licenses:${Versions.OssLicense.Master}"
    )

    val jackson = listOf(
        "com.fasterxml.jackson.core:jackson-core:${Versions.Util.Jackson}",
        "com.fasterxml.jackson.core:jackson-databind:${Versions.Util.Jackson}",
        "com.fasterxml.jackson.core:jackson-annotations:${Versions.Util.Jackson}",
        "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.Util.Jackson}"
    )

    val retrofit = listOf(
        "com.squareup.okhttp3:okhttp:${Versions.Network.OkHttp}",
        "com.squareup.retrofit2:retrofit:${Versions.Network.Retrofit}"
    )

    val retrofitutil = listOf(
        "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OkHttp}",
        "com.squareup.retrofit2:converter-jackson:${Versions.Network.Retrofit}"
    )

    val room = listOf(
        "androidx.room:room-ktx:${Versions.Jetpack.Room}",
        "androidx.room:room-runtime:${Versions.Jetpack.Room}"
    )

    val compose = listOf(
        "androidx.compose.ui:ui:${Versions.Compose.Master}",
        "androidx.paging:paging-compose:${Versions.Compose.Paging}",
        "androidx.compose.ui:ui-tooling:${Versions.Compose.Master}",
        "com.airbnb.android:lottie-compose:${Versions.Compose.Lottie}",
        "androidx.compose.compiler:compiler:${Versions.Compose.Master}",
        "androidx.compose.material:material:${Versions.Compose.Master}",
        "androidx.activity:activity-compose:${Versions.Compose.Activity}",
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Compose.Lifecycle}",
        "com.google.accompanist:accompanist-swiperefresh:${Versions.Compose.SwipeRefresh}",
        "androidx.constraintlayout:constraintlayout-compose:${Versions.Compose.ConstraintLayout}"
    )

    val util = listOf("io.github.jisungbin:erratum:${Versions.Util.Erratum}")

    object Paging {
        const val Master = "androidx.paging:paging-runtime:${Versions.Jetpack.Paging}"

        // alternatively - without Android dependencies for tests
        const val Domain = "androidx.paging:paging-common:${Versions.Jetpack.Paging}"
    }
}
