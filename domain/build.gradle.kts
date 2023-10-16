plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    // AndroidX
//    implementation("androidx.core:core-ktx:1.11.0")
//    implementation("androidx.activity:activity:1.7.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Hilt
    implementation("javax.inject:javax.inject:1")
}
