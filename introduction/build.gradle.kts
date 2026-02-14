import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.2.0"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.mockk:mockk:1.14.9")
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.2")
}

tasks.test {
    useJUnitPlatform()
}
