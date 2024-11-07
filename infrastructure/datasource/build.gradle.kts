plugins {
    alias(kotlinLibs.plugins.kotlin.jvm)
}

dependencies {
    implementation(thirdPartyLibs.postgres)
    implementation(thirdPartyLibs.exposed.core)
    implementation(thirdPartyLibs.exposed.crypt)
    implementation(thirdPartyLibs.exposed.dao)
    implementation(thirdPartyLibs.exposed.jdbc)
    implementation(thirdPartyLibs.exposed.kotlin.datetime)

    testImplementation(thirdPartyLibs.kotest.runner.junit5.jvm)
    testImplementation(thirdPartyLibs.kotest.assertions.core)

    implementation(project(":domain"))
}
