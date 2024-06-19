plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.c3dev.bagueton"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.c3dev.bagueton"
        minSdk = 27
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.benchmark.macro)
    val navVersion = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$navVersion")
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
    //Requete
//    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.12")
    //Parsing JSON
    implementation("com.google.code.gson:gson:2.10.1")
    //Téléchargement d'image
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")
    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")
    //Utilisation générale
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1-Beta")
    //LifeCycleScope
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    //ViewModelScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    // Coil-Compose pour le chargement d'images dans Jetpack Compose.
    implementation( "io.coil-kt:coil-compose:2.6.0")
    // Ajoute plus de choix aux icones
    implementation ("androidx.compose.material:material-icons-core:1.0.1") // Vérifiez la version la plus récente
    implementation ("androidx.compose.material:material-icons-extended:1.0.1")
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //Coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5")
    implementation( "com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
}