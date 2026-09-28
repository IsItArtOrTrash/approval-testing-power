plugins {
    java
    idea
    val kotlinPluginVersion = "2.4.20"
    kotlin("jvm") version kotlinPluginVersion
}

group = "io.github.isitartortrash"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // JSON serialization
    val jacksonVersion = "2.22.3"
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.google.code.gson:gson:2.14.0")

    val junitVersion = "6.1.3"
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    val assertjVersion = "3.27.7"
    testImplementation("org.assertj:assertj-core:$assertjVersion")

    val jqwikVersion = "1.10.1"
    testImplementation("net.jqwik:jqwik:$jqwikVersion")

    testImplementation("com.approvaltests:approvaltests:31.0.0")
    testImplementation("org.apache.commons:commons-lang3:3.20.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(26)) }
    sourceCompatibility = JavaVersion.VERSION_26
    targetCompatibility = JavaVersion.VERSION_26
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_26)
    }
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of("26"))
    }
}

tasks.withType<Test> { useJUnitPlatform() }