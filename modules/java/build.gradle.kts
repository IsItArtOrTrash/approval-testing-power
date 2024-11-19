plugins {
    java
    idea
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
    implementation("com.google.code.gson:gson:2.11.0")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    val junitVersion = "5.10.3"
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    val assertjVersion = "3.26.3"
    testImplementation("org.assertj:assertj-core:$assertjVersion")

    val jqwikVersion = "1.9.1"
    testImplementation("net.jqwik:jqwik:$jqwikVersion")

    testImplementation("org.mockito:mockito-core:5.14.2")
    testImplementation("org.mockito:mockito-junit-jupiter:5.14.2")
    testImplementation("org.apache.commons:commons-lang3:3.17.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }

tasks.withType<Test> { useJUnitPlatform() }