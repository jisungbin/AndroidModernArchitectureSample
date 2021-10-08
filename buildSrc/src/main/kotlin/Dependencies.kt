import org.gradle.api.JavaVersion

object Application {
    const val minSdk = 24
    const val targetSdk = 30
    const val compileSdk = 30
    const val jvmTarget = "11"
    const val versionCode = 1
    const val versionName = "1.0.0"

    val targetCompat = JavaVersion.VERSION_11
    val sourceCompat = JavaVersion.VERSION_11
}

object Versions {
    object Essential {
        const val Kotlin = "1.5.30"
        const val Coroutines = "1.5.2"
        const val Gradle = "7.1.0-alpha05"
        const val Ktx = "1.6.0"
    }

    object Compose {
        const val Master = "1.0.2"
        const val Activity = "1.3.1"
        const val LifeCycle = "1.0.0-alpha07"
    }

    object Ui {
        const val Material = "1.4.0"
    }
}

object Dependencies {
    val essential = listOf(
        "androidx.core:core-ktx:${Versions.Essential.Ktx}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Essential.Coroutines}"
    )

    val compose = listOf(
        "androidx.compose.ui:ui:${Versions.Compose.Master}",
        "androidx.compose.ui:ui-tooling:${Versions.Compose.Master}",
        "androidx.compose.compiler:compiler:${Versions.Compose.Master}",
        "androidx.compose.material:material:${Versions.Compose.Master}",
        "androidx.activity:activity-compose:${Versions.Compose.Activity}",
        "androidx.compose.runtime:runtime-livedata:${Versions.Compose.Master}",
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Compose.LifeCycle}"
    )

    val ui = listOf(
        "com.google.android.material:material:${Versions.Ui.Material}"
    )
}
