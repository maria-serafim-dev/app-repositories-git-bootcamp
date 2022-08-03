object Android{
    object Versions {
        const val core = "1.8.0"
        const val appCompat = "1.4.2"
        const val lifecycle = "2.4.1"
    }

    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
}

object View{
    object Versions {
        const val material = "1.6.1"
        const val constraint = "2.1.4"
    }

    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
}

object UnitTest {
    object Versions {
        const val junit = "4.13.2"
        const val ext = "1.1.3"
        const val espresso = "3.4.0"
    }

    const val junit = "junit:junit:${Versions.junit}"
    const val ext = "androidx.test.ext:junit:${Versions.ext}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object Navigation {
    object Versions {
        const val core = "2.4.2"
    }

    const val core = "androidx.navigation:navigation-ui-ktx:${Versions.core}"
    const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.core}"
}

object Retrofit {
    object Versions {
        const val retrofit = "2.9.0"
    }

    const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
}

object OkHttp {
    object Versions {
        const val okhttp = "4.9.3"
    }

    const val core = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
}

object Glide {
    object Versions {
        const val glide = "4.13.2"
    }

    const val core = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object Shimmer {
    object Versions {
        const val shimmer = "0.5.0"
    }

    const val core = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
}

object Koin {
    object Versions {
        const val koin = "3.2.0"
        const val ext = "3.0.1"
    }

    const val core = "io.insert-koin:koin-android:${Versions.koin}"
    const val test = "io.insert-koin:koin-test:${Versions.koin}"
    const val junit = "io.insert-koin:koin-test-junit4:${Versions.koin}"
    const val ext = "io.insert-koin:koin-android-ext:${Versions.ext}"

}

object TapTargetView {
    object Versions {
        const val taptargetview = "1.13.3"
    }

    const val core = "com.getkeepsafe.taptargetview:taptargetview:${Versions.taptargetview}"

}


object Coroutines {
    object Versions {
        const val coroutines = "1.6.1"
    }

    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

}

object Lifecycle {
    object Versions {
        const val extensions = "2.2.0"
        const val viewmodel = "1.6.1"
    }

    const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.extensions}"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel}"

}