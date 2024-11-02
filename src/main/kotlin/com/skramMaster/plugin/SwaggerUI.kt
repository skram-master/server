package com.skramMaster.plugin

import io.github.smiley4.ktorswaggerui.SwaggerUI
import io.github.smiley4.ktorswaggerui.routing.openApiSpec
import io.github.smiley4.ktorswaggerui.routing.swaggerUI
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureSwaggerUI() {
    if (developmentMode) {
        install(SwaggerUI) {
            info {
                title = "Example API"
                version = "latest"
                description = "Example API for testing and demonstration purposes."
            }
            server {
                url = "http://localhost:8080"
                description = "Development Server"
            }
        }

        routing {
            route("openapi.json") {
                openApiSpec()
            }
            // Create a route for the swagger-ui using the openapi-spec at "/api.json".
            route("swagger") {
                swaggerUI("/openapi.json")
            }
        }
    }
}