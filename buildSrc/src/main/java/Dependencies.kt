object Apps {
    const val compileSdk = 33
    const val minSdk = 24
    const val targetSdk = 33
    var versionCode = 9
    var versionName = "1.0.0"
    const val pacakageName = "com.playtogether_android.app"
}

object Versions {
    const val kotlin_version = "1.7.20"
    const val core_ktx_version = "1.9.0"
    const val appcompat_version = "1.6.0"
    const val material_design_version = "1.3.0"
    const val constraint_layout_version = "2.1.3"
    const val datastore_version = "1.0.0-beta01"
    const val lifecycle_ktx_version = "2.4.0-alpha02"
    const val activity_ktx = "1.6.1"
    const val fragment_ktx = "1.5.5"
    const val navigation_version = "2.3.5"
    const val security_version = "1.0.0"
    const val biometric_version = "1.1.0"
    const val kotlinx_serialization_version = "1.0.1"
    const val cardview_version = "1.0.0"

    const val glide_version = "4.11.0"
    const val glide_compiler_version = "4.11.0"
    const val gson_version = "2.8.6"
    const val retrofit_version = "2.9.0"
    const val okhttp_version = "4.9.1"

    const val junit_version = "4.13.2"
    const val espresso_version = "3.5.1"
    const val android_test_version = "1.1.5"

    const val jvm_version = "1.8"
}

object KotlinDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
}

object AndroidXDependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout_version}"
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.datastore_version}"
    const val dataStoreCore =
        "androidx.datastore:datastore-preferences-core:${Versions.datastore_version}"
    const val viewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_ktx_version}"
    const val activityKtx =
        "androidx.activity:activity-ktx:${Versions.activity_ktx}"
    const val fragmentKtx =
        "androidx.fragment:fragment-ktx:${Versions.fragment_ktx}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_version}"
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_version}"
    const val security = "androidx.security:security-crypto:${Versions.security_version}"
    const val kotlinxSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinx_serialization_version}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardview_version}"
    const val liveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_ktx_version}"
}

object MaterialDesignDependencies {
    const val materialDesign =
        "com.google.android.material:material:${Versions.material_design_version}"
}

object ThirdPartyDependencies {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    const val gson = "com.google.code.gson:gson:${Versions.gson_version}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofitGsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"
    const val okhttpBOM = "com.squareup.okhttp3:okhttp-bom:${Versions.okhttp_version}"
    const val okhttp = "com.squareup.okhttp3:okhttp"
    const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor"
    const val lottieSwipeRefreshLayout = "com.github.nabil6391:LottieSwipeRefreshLayout:1.0.0"
}

object TestDependencies {
    const val jUnit = "junit:junit:${Versions.junit_version}"
    const val androidTest = "androidx.test.ext:junit:${Versions.android_test_version}"
    const val androidKtxTest = "androidx.test.ext:junit-ktx:${Versions.android_test_version}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"
}

