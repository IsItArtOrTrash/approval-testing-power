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
    val jacksonVersion = "2.22.3"
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("com.google.code.gson:gson:2.14.0")

    val lombokVersion = "1.18.48"
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    val junitVersion = "6.1.3"
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    val assertjVersion = "3.27.7"
    testImplementation("org.assertj:assertj-core:$assertjVersion")

    val jqwikVersion = "1.10.1"
    testImplementation("net.jqwik:jqwik:$jqwikVersion")

    testImplementation("com.approvaltests:approvaltests:24.9.0")
    val mockitoVersion = "5.24.0"
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.mockito:mockito-junit-jupiter:$mockitoVersion")
    testImplementation("org.apache.commons:commons-lang3:3.20.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(26)) } }

tasks.withType<Test> { useJUnitPlatform() }