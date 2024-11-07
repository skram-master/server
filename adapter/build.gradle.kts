plugins {
    alias(kotlinLibs.plugins.kotlin.jvm)
}

dependencies {
    testImplementation(thirdPartyLibs.kotest.runner.junit5.jvm)
    testImplementation(thirdPartyLibs.kotest.assertions.core)

    implementation(project(":domain"))
}
