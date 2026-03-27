import org.gradle.kotlin.dsl.implementation

plugins {
    kotlin("jvm") version "2.3.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.mongodb:mongodb-driver-sync:4.9.0")
}

kotlin {
    jvmToolchain(20)
}

tasks.test {
    useJUnitPlatform()
}