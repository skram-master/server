package com.skramMaster.infrastructure.datasource.repository

import com.skramMaster.domain.model.Article
import com.skramMaster.domain.repository.ArticleRepository
import com.skramMaster.infrastructure.datasource.dao.article.ArticleDao

class ArticleRepositoryImpl : ArticleRepository {
    override suspend fun getArticles(): List<Article> {
        return ArticleDao.all().map { it.toArticle() }
    }

    override suspend fun createArticle(article: Article): Article {
        return ArticleDao.new {
            title = article.title
            content = article.content
        }.toArticle()
    }
}

private fun ArticleDao.toArticle(): Article {
    return Article(
        id = id.value,
        title = title,
        content = content
    )
}
