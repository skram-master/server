package com.skramMaster.infrastructure.ktorapp.route.v1

import com.skramMaster.controller.article.ArticleController
import com.skramMaster.dto.article.ArticleGetV1Request
import com.skramMaster.dto.article.ArticleV1Response
import com.skramMaster.infrastructure.ktorapp.extension.get
import com.skramMaster.infrastructure.ktorapp.extension.post
import com.skramMaster.resource.article.ArticlesV1
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route

fun Route.articleRouteV1(articleController: ArticleController) {
    getArticles(articleController = articleController)
    createArticles(articleController = articleController)
}

private fun Route.getArticles(articleController: ArticleController) {
    get<ArticlesV1>(
        {
            tags = ArticlesV1.tags
            response {
                HttpStatusCode.OK to {
                    description = "Successful Request"
                    body<List<ArticleV1Response>> {
                        example("default") {
                            value = listOf(
                                ArticleV1Response(1, "Article 1", "Content 1"),
                                ArticleV1Response(2, "Article 2", "Content 2"),
                            )
                        }
                    }
                }
                HttpStatusCode.InternalServerError to {
                    description = "Something unexpected happened"
                }
            }
        },
    ) {
        call.respond(articleController.getArticles())
    }
}

fun Route.createArticles(articleController: ArticleController) {
    post<ArticlesV1.New>(
        {
            tags = ArticlesV1.tags
            request {
                body<ArticleGetV1Request>()
            }
            response {
                HttpStatusCode.OK to {
                    description = "Successful Request"
                    body<ArticleV1Response> {
                        example("default") {
                            value = ArticleV1Response(1, "Created Article 1", "Content 1")
                        }
                    }
                }
                HttpStatusCode.InternalServerError to {
                    description = "Something unexpected happened"
                }
            }
        },
    ) {
        val request = call.receive<ArticleGetV1Request>()
        call.respond(articleController.createArticle(request))
    }
}
