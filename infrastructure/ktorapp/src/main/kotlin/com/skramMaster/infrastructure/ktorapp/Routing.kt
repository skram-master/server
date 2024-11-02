package com.skramMaster.infrastructure.ktorapp

import com.skramMaster.infrastructure.ktorapp.route.v1.resourceRouteV1
import io.ktor.server.application.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing


fun Application.configureRouting() {
    install(Resources)

    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
        route("api/v1") {
            resourceRouteV1()
        }
    }
}

