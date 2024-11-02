package com.skramMaster.route

import com.skramMaster.extension.get
import io.ktor.http.HttpStatusCode
import io.ktor.resources.Resource
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import kotlinx.serialization.Serializable

@Serializable
@Resource("/articles")
private class Articles(val sort: String? = "new")

@Serializable
data class ArticlesResponse(val id: Int, val title: String, val content: String)

fun Route.resourceRoute() {
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
            response {
                HttpStatusCode.OK to {
                    description = "Successful Request"
                    body<ArticlesResponse> { }
                }
                HttpStatusCode.InternalServerError to {
                    description = "Something unexpected happened"
                }
            }
        }
    ) { articles ->
        val articlesResponse = listOf(
            ArticlesResponse(1, "Article 1", "Content 1"),
            ArticlesResponse(2, "Article 2", "Content 2"),
            ArticlesResponse(3, "Article 3", "Content 3")
        )
        call.respond(articlesResponse)
    }
}