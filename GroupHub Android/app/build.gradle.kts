plugins {
    id("com.android.application")
}

android {
    namespace = "iss.workshop.androidgrouphub"
    compileSdk = 34

    defaultConfig {
        applicationId = "iss.workshop.androidgrouphub"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}



dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")

    // Ok Http
    implementation("com.squareup.okhttp3:okhttp:4.4.1")

    // Google Gson
    implementation("com.google.code.gson:gson:2.8.6")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")

    // circule menu
    implementation("com.github.Hitomis:CircleMenu:v1.1.0")

    // Tencent Location
    implementation ("com.tencent.map.geolocation:TencentLocationSdk-openplatform:7.3.0")

    // 动态权限请求
    implementation ("com.tbruyelle.rxpermissions2:rxpermissions:0.9.4@aar")
    implementation ("io.reactivex.rxjava2:rxandroid:2.0.2")
    implementation ("io.reactivex.rxjava2:rxjava:2.0.0")






    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}