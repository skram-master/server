val appVersion: String by project

plugins {
    application
    alias(kotlinLibs.plugins.kotlin.jvm) apply (false)
    alias(ktorLibs.plugins.io.ktor) apply (false)
    alias(thirdPartyLibs.plugins.jooq.codegen) apply (false)
}

group = "com.skramMaster"
version = appVersion

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {

}
