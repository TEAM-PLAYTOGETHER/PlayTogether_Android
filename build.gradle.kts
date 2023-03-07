val keyStore by extra("/Users/chan/Desktop/Coder/PLTOKEY/release/release-apk-key")
val password by extra("123123")
val name by extra("plto")
val unitName by extra("/Users/chan/Desktop/Coder/PLTOKEY/release/Untitled")
val unitPassword by extra(password)
val keyname by extra("plto")
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version by extra("1.0.0-release")

    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.4")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
        //LottieSwipeRefreshLayout
        maven("https://jitpack.io")
        maven("https://devrepo.kakao.com/nexus/content/groups/public/")
        maven("https://maven.google.com")
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}