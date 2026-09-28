plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "approval-testing-power"

include("modules:java")
findProject(":modules:java")?.name = "java"
