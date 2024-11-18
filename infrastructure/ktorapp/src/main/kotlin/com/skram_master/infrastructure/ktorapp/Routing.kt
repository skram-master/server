package com.skram_master.infrastructure.ktorapp

import com.skram_master.controller.article.ArticleController
import com.skram_master.infrastructure.ktorapp.route.v1.articleRouteV1
import io.ktor.server.application.Application
import io.ktor.server.application.install
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
            articleRouteV1(articleController = articleController)
        }
    }
}
