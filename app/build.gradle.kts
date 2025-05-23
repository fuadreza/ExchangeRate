import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  kotlin("plugin.serialization")
  id("com.google.devtools.ksp")
  id("com.google.dagger.hilt.android")
}

// Get local.properties
val currencyFreaksApiKey: String = gradleLocalProperties(rootDir, providers).getProperty("CURRENCY_FREAKS_API_KEY")

android {
  namespace = "top.fuadreza.tukaruang"
  compileSdk = 35

  defaultConfig {
    applicationId = "top.fuadreza.tukaruang"
    minSdk = 24
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    buildConfigField("String", "API_KEY", "\"$currencyFreaksApiKey\"")
  }

  buildTypes {
    getByName("debug") {
      buildConfigField("String", "API_KEY", currencyFreaksApiKey)
    }
    release {
      isMinifyEnabled = false
      isDebuggable = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      buildConfigField("String", "API_KEY", currencyFreaksApiKey)
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
    compose = true
    buildConfig = true
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)

  // Coil
  implementation(libs.coil.compose)
  implementation(libs.coil.network.okhttp)

  // Serialization
  implementation(libs.kotlinx.serialization.json)

  // Room
  implementation(libs.androidx.room.runtime)
  ksp(libs.androidx.room.compiler)

  // optional - Kotlin Extensions and Coroutines support for Room
  implementation(libs.androidx.room.ktx)

  // Hilt
  implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
  implementation(libs.androidx.hilt.navigation.compose)

  // Retrofit
  implementation(libs.retrofit)
  implementation(libs.converter.gson)
  implementation(libs.retrofit2.kotlinx.serialization.converter)

  // Okhttp
  implementation(libs.logging.interceptor)
}