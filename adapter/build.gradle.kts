plugins {
    alias(kotlinLibs.plugins.kotlin.jvm)
    alias(kotlinLibs.plugins.kotlin.serialization)
}

dependencies {
    implementation(kotlinLibs.kotlin.serialization)

    testImplementation(thirdPartyLibs.kotest.runner.junit5.jvm)
    testImplementation(thirdPartyLibs.kotest.assertions.core)

    implementation(project(":domain"))
}
