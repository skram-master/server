plugins {
    alias(kotlinLibs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.io.ktor)
}

dependencies {
    implementation(thirdPartyLibs.postgres)
    implementation(thirdPartyLibs.exposed.core)
    implementation(thirdPartyLibs.exposed.crypt)
    implementation(thirdPartyLibs.exposed.dao)
    implementation(thirdPartyLibs.exposed.jdbc)
    implementation(thirdPartyLibs.exposed.kotlin.datetime)
}


