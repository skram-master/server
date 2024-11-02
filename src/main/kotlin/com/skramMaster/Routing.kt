package com.skramMaster

import com.skramMaster.extension.get
import com.skramMaster.route.Articles
import io.ktor.server.application.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing


fun Application.configureRouting() {
    install(Resources)

    routing {
        get("/") {
            call.respondText("Hello, world!")
        }

        get<Articles>(
            {
                description = "Get all articles"
                request {
                    queryParameter<String>(Articles::sort.name) {
                        description = "Sort articles by name"
                        required = false
                        example("default") {
                            value = "article1"
                        }
                    }
                }
                response {}
            }
        ) { resources ->
            call.respondText("Articles: ${resources.sort}")
        }
    }
}
