plugins {
    kotlin("jvm") version "1.9.21"
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jgrapht:jgrapht-core:1.5.2")
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}
