package com.skram_master.infrastructure.datasource.repository

import com.skram_master.domain.model.article.Article
import com.skram_master.domain.repository.ArticleRepository
import com.skram_master.infrastructure.datasource.dao.article.ArticleDAO

class ArticleRepositoryImpl : ArticleRepository {
    override suspend fun getArticles(): List<Article> {
        return ArticleDAO.all().map { it.toArticle() }
    }

    override suspend fun createArticle(article: Article): Article {
        return ArticleDAO.new {
            title = article.title
            content = article.content
        }.toArticle()
    }
}

private fun ArticleDAO.toArticle(): Article {
    return Article(
        id = id.value,
        title = title,
        content = content,
    )
}
