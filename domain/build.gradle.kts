plugins {
    alias(kotlinLibs.plugins.kotlin.jvm)
}

dependencies {
    implementation(kotlinLibs.kotlin.coroutine)

    testImplementation(thirdPartyLibs.kotest.runner.junit5.jvm)
    testImplementation(thirdPartyLibs.kotest.assertions.core)
}
