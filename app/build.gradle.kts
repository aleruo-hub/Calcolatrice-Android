plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.calcolatrice"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.calcolatrice"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Allinea le librerie Kotlin transitive ed evita classi duplicate.
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22")
    }

    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.activity:activity:1.9.3")
    implementation("androidx.core:core:1.15.0")
}
