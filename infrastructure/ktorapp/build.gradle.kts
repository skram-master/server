import org.gradle.kotlin.dsl.ktor


plugins {
    application
    alias(kotlinLibs.plugins.kotlin.serialization)
    alias(ktorLibs.plugins.io.ktor)
}


application {
    mainClass.set("io.ktor.server.netty.EngineMain")
    // This arg passes the app when running the app from the gradle task
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(ktorLibs.ktor.server.core.jvm)
    implementation(ktorLibs.ktor.server.netty.jvm)
    implementation(ktorLibs.ktor.server.config.yaml)

    implementation(ktorLibs.ktor.server.http.redirect.jvm)
    implementation(ktorLibs.ktor.server.default.headers.jvm)

    implementation(ktorLibs.ktor.server.websockets.jvm)
    implementation(ktorLibs.ktor.server.auth.jvm)
    implementation(ktorLibs.ktor.server.auth.jwt.jvm)
    implementation(ktorLibs.ktor.server.cors.jvm)
    implementation(ktorLibs.ktor.server.csrf.jvm)

    implementation(ktorLibs.ktor.server.call.logging.jvm)
    implementation(thirdPartyLibs.logback.classic)

    implementation(thirdPartyLibs.ktor.swagger.ui)

    implementation(ktorLibs.ktor.server.resources.jvm)
    implementation(ktorLibs.ktor.server.content.negotiation.jvm)
    implementation(ktorLibs.ktor.serialization.kotlinx.json.jvm)
}

ktor {
    fatJar {
        archiveFileName.set("fat.jar")
    }
}
