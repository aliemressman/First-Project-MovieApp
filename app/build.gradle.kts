plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
}

android {
    namespace = "com.aliemressman.movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aliemressman.movieapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        dataBinding = true
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    val lifeCycleExtensionVersion = "1.1.1"
    val supportVersion = "28.0.0"
    val retrofitVersion = "2.8.1"
    val glideVersion = "4.11.0"
    val rxJavaVersion = "2.2.8"
    val rxAndroidVersion = "2.1.1"
    val roomVersion = "2.6.1"
    val navVersion = "2.7.6"
    val preference_version = "1.2.1"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("android.arch.lifecycle:extensions:$lifeCycleExtensionVersion")
    implementation ("androidx.room:room-runtime:$roomVersion")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    kapt ("androidx.room:room-compiler:$roomVersion")
    implementation ("androidx.room:room-ktx:$roomVersion")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")
    implementation ("io.reactivex.rxjava2:rxjava:$rxJavaVersion")
    implementation ("io.reactivex.rxjava2:rxandroid:$rxAndroidVersion")
    implementation ("com.github.bumptech.glide:glide:$glideVersion")
    implementation("androidx.preference:preference-ktx:$preference_version")

}