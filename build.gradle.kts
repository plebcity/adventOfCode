plugins {
    kotlin("jvm") version "2.2.21"
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jgrapht:jgrapht-core:1.5.2")
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}
