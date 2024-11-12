plugins {
    alias(kotlinLibs.plugins.kotlin.jvm)
}

dependencies {
    implementation(kotlinLibs.kotlin.coroutine)
    implementation(thirdPartyLibs.postgres)
    implementation(thirdPartyLibs.exposed.core)
    implementation(thirdPartyLibs.exposed.crypt)
    implementation(thirdPartyLibs.exposed.dao)
    implementation(thirdPartyLibs.exposed.jdbc)
    implementation(thirdPartyLibs.exposed.kotlin.datetime)

    implementation(project(":domain"))
}
