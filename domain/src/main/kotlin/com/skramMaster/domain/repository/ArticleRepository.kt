package com.skramMaster.domain.repository

import com.skramMaster.domain.model.Article

interface ArticleRepository {
    suspend fun getArticles(): List<Article>
    suspend fun createArticle(article: Article): Article
}