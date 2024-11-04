package com.skramMaster.infrastructure.ktorapp.route.v1

import com.skramMaster.infrastructure.ktorapp.extension.get
import io.ktor.http.HttpStatusCode
import io.ktor.resources.Resource
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import kotlinx.serialization.Serializable

@Serializable
@Resource("/articles")
private data class ArticlesV1(val sort: SortOrder? = SortOrder.default) {
    @Suppress("EnumEntryName")
    enum class SortOrder {
        new,
        old,
        default,
    }

    @Serializable
    @Resource("{id}")
    data class Id(val parent: ArticlesV1 = ArticlesV1(), val id: Int)

    companion object {
        val tags = listOf("api/v1/articles")
    }
}

@Serializable
private data class ArticlesResponse(val id: Int, val title: String, val content: String)

fun Route.resourceRouteV1() {
    getArticles()
    getArticleById()
}

private fun Route.getArticles() {
    get<ArticlesV1>(
        {
            tags = ArticlesV1.tags
            request {
                queryParameter<ArticlesV1.SortOrder>(ArticlesV1::sort.name) {
                    required = false
                    example("default") {
                        value = ArticlesV1.SortOrder.default
                    }
                }
            }
            response {
                HttpStatusCode.OK to {
                    description = "Successful Request"
                    body<List<ArticlesResponse>> {
                        example("default") {
                            value = listOf(
                                ArticlesResponse(1, "Article 1", "Content 1"),
                                ArticlesResponse(2, "Article 2", "Content 2"),
                            )
                        }
                    }
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
        ).let {
            when (articles.sort) {
                ArticlesV1.SortOrder.new -> it.sortedBy { it.id }
                ArticlesV1.SortOrder.old -> it.sortedByDescending { it.id }
                ArticlesV1.SortOrder.default -> it
                else -> it
            }
        }
        call.respond(articlesResponse)
    }
}

fun Route.getArticleById() {
    get<ArticlesV1.Id>(
        {
            tags = ArticlesV1.tags
            request {
                queryParameter<String>(ArticlesV1.Id::id.name) {
                    description = "Get article by id"
                    required = true
                    example("default") {
                        value = 1
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
    ) { articleId ->
        val article = ArticlesResponse(articleId.id, "Article ${articleId.id}", "Content ${articleId.id}")
        call.respond(article)
    }
}