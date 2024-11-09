package com.skramMaster.infrastructure.ktorapp.route.v1

import com.skraMaster.controller.article.ArticleController
import com.skraMaster.dto.article.ArticleGetV1Request
import com.skraMaster.dto.article.ArticleV1Response
import com.skramMaster.infrastructure.ktorapp.extension.get
import com.skramMaster.infrastructure.ktorapp.extension.post
import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/articles")
class ArticlesV1 {

    @Serializable
    @Resource("/new")
    class New(val parent: ArticlesV1 = ArticlesV1())

    companion object {
        val tags = listOf("Articles")
    }
}

fun Route.resourceRouteV1(articleController: ArticleController) {
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
        }
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
        }
    ) {
        val request = call.receive<ArticleGetV1Request>()
        call.respond(articleController.createArticle(request))
    }
}