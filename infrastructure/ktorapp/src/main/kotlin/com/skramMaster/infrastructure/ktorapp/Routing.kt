package com.skramMaster.infrastructure.ktorapp

import com.skraMaster.controller.article.ArticleController
import com.skramMaster.infrastructure.ktorapp.route.v1.resourceRouteV1
import io.ktor.server.application.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject


fun Application.configureRouting() {
    install(Resources)

    val articleController: ArticleController by inject()

    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
        route("api/v1") {
            resourceRouteV1(articleController=articleController)
        }
    }
}

