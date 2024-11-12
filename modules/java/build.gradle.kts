plugins {
    java
    idea
    val kotlinPluginVersion = "2.0.21"
    kotlin("jvm") version kotlinPluginVersion
}

group = "io.github.isitartortrash"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // JSON serialization
    val jacksonVersion = "2.18.1"
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.google.code.gson:gson:2.11.0")

    val junitVersion = "5.10.3"
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    val assertjVersion = "3.26.3"
    testImplementation("org.assertj:assertj-core:$assertjVersion")

    val jqwikVersion = "1.9.1"
    testImplementation("net.jqwik:jqwik:$jqwikVersion")

    testImplementation("com.approvaltests:approvaltests:24.9.0")
    testImplementation("org.apache.commons:commons-lang3:3.17.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of("21"))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}

tasks.withType<Test> { useJUnitPlatform() }