import org.gradle.kotlin.dsl.ext
import org.gradle.kotlin.dsl.ktor

val appVersion: String by project

plugins {
    application
    alias(kotlinLibs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.io.ktor)
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
    implementation(ktorLibs.ktor.server.core.jvm)
    implementation(ktorLibs.ktor.server.content.negotiation.jvm)
    implementation(ktorLibs.ktor.serialization.kotlinx.json.jvm)
    implementation(ktorLibs.ktor.server.auth.jvm)
    implementation(ktorLibs.ktor.server.http.redirect.jvm)
    implementation(ktorLibs.ktor.server.websockets.jvm)
    implementation(ktorLibs.ktor.server.cors.jvm)
    implementation(ktorLibs.ktor.server.csrf.jvm)
    implementation(ktorLibs.ktor.server.call.logging.jvm)
    implementation(ktorLibs.ktor.server.swagger.jvm)
    implementation(ktorLibs.ktor.server.default.headers.jvm)
    implementation(ktorLibs.ktor.server.resources.jvm)
    implementation(ktorLibs.ktor.server.auth.jwt.jvm)
    implementation(ktorLibs.ktor.server.netty.jvm)
    implementation(ktorLibs.ktor.server.config.yaml)
}

ktor {
    fatJar {
        archiveFileName.set("fat.jar")
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

