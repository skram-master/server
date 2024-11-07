package com.skraMaster.controller.article

import com.skraMaster.dto.article.ArticleRequest
import com.skraMaster.dto.article.ArticleResponse
import com.skramMaster.domain.model.Article
import com.skramMaster.domain.service.ArticleService

class ArticleController(
    private val articleServiceImpl: ArticleService
) {
    fun getArticles(): List<ArticleResponse> {
        return articleServiceImpl.getArticles().map {
            ArticleResponse(
                id = it.id,
                title = it.title,
                content = it.content
            )
        }
    }

    fun createArticle(articleRequest: ArticleRequest) {
        articleServiceImpl.createArticle(
            article = Article(
                title = articleRequest.title,
                content = articleRequest.content,
            ),
        )
    }
}