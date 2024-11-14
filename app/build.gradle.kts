plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "2.0.0"
}

android {
    namespace = "com.example.matule"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.matule"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.7.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.navigation.compose)

    implementation(platform("io.github.jan-tennert.supabase:bom:3.0.1"))
    implementation(libs.postgrest.kt)
    implementation(libs.auth.kt)
    implementation(libs.realtime.kt)
    implementation("io.ktor:ktor-client-cio:3.0.1")
    implementation ("io.github.jan-tennert.supabase:storage-kt:3.0.1")
    implementation(platform("io.github.jan-tennert.supabase:bom:2.5.4"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.5.4")
    implementation("io.ktor:ktor-client-android:2.3.12")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.5.4")
    implementation("io.github.jan-tennert.supabase:realtime-kt:2.5.4")
    implementation("io.github.jan-tennert.supabase:supabase-kt:2.5.4")
    implementation("io.github.jan-tennert.supabase:storage-kt:2.5.4")
    implementation("io.github.jan-tennert.supabase:functions-kt:2.5.4")
    implementation("io.ktor:ktor-client-java:2.3.11")
    implementation ("com.yandex.android:maps.mobile:4.8.1-full")
    implementation("ru.sulgik.mapkit:yandex-mapkit-kmp:0.1.1")
    implementation("ru.sulgik.mapkit:yandex-mapkit-kmp-compose:0.1.1")

    implementation("com.google.zxing:core:3.2.1" )


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}