package com.skram_master.controller.article

import com.skram_master.domain.model.Article
import com.skram_master.domain.service.ArticleService
import com.skram_master.dto.article.ArticleGetV1Request
import com.skram_master.dto.article.ArticleV1Response

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
