import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    signingConfigs {
        create("release") {
            storeFile = file(rootProject.extra["keyStore"] as String)
            storePassword = rootProject.extra["password"] as String
            keyAlias = rootProject.extra["name"] as String
            keyPassword = rootProject.extra["password"] as String
        }
        create("unit1") {
            storeFile = file(rootProject.extra["unitName"] as String)
            storePassword = rootProject.extra["unitPassword"] as String
            keyAlias = rootProject.extra["keyname"] as String
            keyPassword = rootProject.extra["unitPassword"] as String
        }
    }
    compileSdk = Apps.compileSdk
    buildToolsVersion = "31.0.0"

    defaultConfig {
        applicationId = Apps.pacakageName
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        manifestPlaceholders["kakaokey"] = getApiKey("kakao_key_manifest")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", getBaseUrl("base_url"))
        buildConfigField("String", "SOCKET_URL", getSocketUrl("socket_url"))
        buildConfigField("String", "SUBWAY_URL", getSubwayUrl("SUBWAY_URL"))
        buildConfigField("String", "KAKAOKEY", getApiKey("kakao_key"))
        buildConfigField("String","GOOGLE_URL",getBaseUrl("GOOGLE_URL"))
        //buildConfigField("String", "GOOGLE_CLIENT_ID", getApiKey("google_client_id"))
        //buildConfigField("String","GOOGLEKEY",getApiKey("google_key"))
        buildConfigField("String", "GOOGLE_CLIENT_ID", getApiKey("google_client_id"))
        buildConfigField("String","GOOGLE_CLIENT_SECRET",getApiKey("google_client_secret"))

    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("release") {
            manifestPlaceholders += mapOf()
            manifestPlaceholders["appName"] = "@string/app_name"
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("unit1")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    namespace = "com.playtogether_android.app"
}


fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}

fun getSubwayUrl(value: String): String {
    return gradleLocalProperties(rootDir).getProperty(value)
}

fun getBaseUrl(value: String): String {
    return gradleLocalProperties(rootDir).getProperty(value)
}

fun getSocketUrl(value: String): String {
    return gradleLocalProperties(rootDir).getProperty(value)
}


dependencies {

    implementation(KotlinDependencies.kotlin)
    implementation(AndroidXDependencies.appCompat)
    implementation(AndroidXDependencies.coreKtx)
    implementation(MaterialDesignDependencies.materialDesign)
    implementation(AndroidXDependencies.constraintLayout)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation(project(":domain"))
    implementation(project(":data"))

    //bad request log detail show
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //sercurity
    implementation("androidx.security:security-crypto-ktx:1.1.0-alpha04")

    //kakao login
    implementation("com.kakao.sdk:v2-user:2.9.0")

    //google login
    implementation("com.google.android.gms:play-services-auth:20.4.1")

    //moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation("com.squareup.moshi:moshi:1.12.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi:1.12.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")


    //ViewModel
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")


// DataStore
    implementation(AndroidXDependencies.dataStore)
    implementation(AndroidXDependencies.dataStoreCore)


// Android KTX
    implementation(AndroidXDependencies.fragmentKtx)
    implementation(AndroidXDependencies.activityKtx)
    implementation(AndroidXDependencies.viewModelKtx)
    implementation(AndroidXDependencies.liveDataKtx)

// SwipeRefreshLayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
    implementation("com.github.nabil6391:LottieSwipeRefreshLayout:1.0.0")
    implementation("com.airbnb.android:lottie:5.2.0")

// Glide
    implementation(ThirdPartyDependencies.glide)
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.security:security-crypto:1.1.0-alpha04")
//    implementation("com.github.bumptech.glide:okhttp3-integration:4.11.0")

// Navigation
    implementation(AndroidXDependencies.navigation)
    implementation(AndroidXDependencies.navigationFragment)

// Gson
    implementation(ThirdPartyDependencies.gson)

// Okhttp
    implementation(platform(ThirdPartyDependencies.okhttpBOM))
    implementation(ThirdPartyDependencies.okhttp)
    implementation(ThirdPartyDependencies.okhttpInterceptor)

// Retrofit
    implementation(ThirdPartyDependencies.retrofit)
    implementation(ThirdPartyDependencies.retrofitGsonConverter)
    implementation("com.squareup.retrofit2:retrofit-converters:2.4.0")

// Androidx Security
    implementation(AndroidXDependencies.security)
    testImplementation(TestDependencies.jUnit)
    androidTestImplementation(TestDependencies.androidTest)
    androidTestImplementation(TestDependencies.espresso)

//coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

//CardView
    implementation(AndroidXDependencies.cardview)

//recyclerview
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

//annotation
    implementation("org.jetbrains:annotations:20.1.0")
    implementation("androidx.annotation:annotation:1.5.0")

//    hilt
    implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-android-compiler:2.42")

//koin
    implementation("io.insert-koin:koin-core:3.1.2")
    implementation("io.insert-koin:koin-android:3.1.2")
    implementation("io.insert-koin:koin-android-compat:3.1.2")
    testImplementation("io.insert-koin:koin-test:3.1.2")


    //bottomsheet
    implementation("com.google.android.material:material:1.8.0")

    //google
    implementation(platform("com.google.firebase:firebase-bom:29.1.0"))     // Firebase BoM
    implementation("com.google.firebase:firebase-common-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")

    implementation("androidx.work:work-runtime-ktx:2.7.1")


    //dot indicator
    implementation("com.tbuonomo:dotsindicator:4.2")

    //Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    //update
    implementation("com.google.android.play:core:1.10.3")

    //Kapt
    //kapt(KaptDependencies.glide)

    //Socket.io
    implementation("io.socket:socket.io-client:2.0.0") {
        exclude("org.json", "json")
    }

    //okhttp websocket
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")

    //gson
    implementation("com.google.code.gson:gson:2.9.0")

    implementation("com.google.android.flexbox:flexbox:3.0.0")

    //for push alarm
    implementation ("androidx.work:work-runtime-ktx:2.7.1")

    implementation ("com.airbnb.android:lottie:5.2.0")

    //ted permission

    implementation ("io.github.ParkSangGwon:tedpermission-normal:3.3.0")

    // Coroutine
    implementation ("io.github.ParkSangGwon:tedpermission-coroutine:3.3.0")
}