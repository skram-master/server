plugins {
    application
    alias(kotlinLibs.plugins.kotlin.jvm)
    alias(kotlinLibs.plugins.kotlin.serialization)
    alias(ktorLibs.plugins.io.ktor)
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    implementation(ktorLibs.ktor.server.core.jvm)
    implementation(ktorLibs.ktor.server.netty.jvm)
    implementation(ktorLibs.ktor.server.config.yaml)

    implementation(ktorLibs.ktor.server.http.redirect.jvm)
    implementation(ktorLibs.ktor.server.default.headers.jvm)

    implementation(ktorLibs.ktor.server.auth.jvm)
    implementation(ktorLibs.ktor.server.auth.jwt.jvm)
    implementation(ktorLibs.ktor.server.cors.jvm)
    implementation(ktorLibs.ktor.server.csrf.jvm)

    implementation(ktorLibs.ktor.server.call.logging.jvm)
    implementation(thirdPartyLibs.logback.classic)

    implementation(ktorLibs.ktor.server.resources.jvm)
    implementation(ktorLibs.ktor.server.content.negotiation.jvm)
    implementation(ktorLibs.ktor.server.request.validation)
    implementation(ktorLibs.ktor.server.status.pages)
    implementation(ktorLibs.ktor.serialization.kotlinx.json.jvm)

    implementation(thirdPartyLibs.ktor.swagger.ui)

    implementation(thirdPartyLibs.koin.ktor)
    implementation(thirdPartyLibs.koin.logger.slf4j)

    // other module dependencies
    implementation(project(":infrastructure:datasource"))
    implementation(project(":domain"))
    implementation(project(":adapter"))
}

ktor {
    fatJar {
        archiveFileName.set("fat.jar")
    }
}
