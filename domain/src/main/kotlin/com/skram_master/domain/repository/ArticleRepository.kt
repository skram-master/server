package com.skram_master.domain.repository

import com.skram_master.domain.model.article.Article

interface ArticleRepository {
    suspend fun getArticles(): List<Article>
    suspend fun createArticle(article: Article): Article
}
