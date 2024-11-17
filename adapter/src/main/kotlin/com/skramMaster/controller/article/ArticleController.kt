package com.skramMaster.controller.article

import com.skramMaster.domain.model.Article
import com.skramMaster.domain.service.ArticleService
import com.skramMaster.dto.article.ArticleGetV1Request
import com.skramMaster.dto.article.ArticleV1Response

class ArticleController(
    private val articleServiceImpl: ArticleService,
) {
    suspend fun getArticles(): List<ArticleV1Response> {
        return articleServiceImpl.getArticles().map {
            ArticleV1Response(
                id = it.id,
                title = it.title,
                content = it.content,
            )
        }
    }

    suspend fun createArticle(articleRequest: ArticleGetV1Request): ArticleV1Response {
        val createdArticle = articleServiceImpl.createArticle(
            article = Article(
                title = articleRequest.title,
                content = articleRequest.content,
            ),
        )
        return ArticleV1Response(
            id = createdArticle.id,
            title = createdArticle.title,
            content = createdArticle.content,
        )
    }
}
