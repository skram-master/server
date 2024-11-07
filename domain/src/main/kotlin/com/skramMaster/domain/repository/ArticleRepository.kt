package com.skramMaster.domain.repository

import com.skramMaster.domain.model.Article

interface ArticleRepository {
    fun getArticles(): List<Article>
    fun createArticle(article: Article): Article
}