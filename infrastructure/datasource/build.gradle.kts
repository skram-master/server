plugins {
    alias(kotlinLibs.plugins.kotlin.jvm)
    alias(kotlinLibs.plugins.kotlin.serialization)
}

dependencies {
    implementation(kotlinLibs.kotlin.coroutine)
    implementation(kotlinLibs.kotlin.serialization)

    implementation(thirdPartyLibs.postgres)

    implementation(thirdPartyLibs.exposed.core)
    implementation(thirdPartyLibs.exposed.crypt)
    implementation(thirdPartyLibs.exposed.dao)
    implementation(thirdPartyLibs.exposed.jdbc)
    implementation(thirdPartyLibs.exposed.kotlin.datetime)
    implementation(thirdPartyLibs.exposed.json)
    implementation(thirdPartyLibs.exposed.migration)

    implementation(thirdPartyLibs.flyway.core)

    implementation(project(":core"))
    implementation(project(":domain"))
}
