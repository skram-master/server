package com.skramMaster.infrastructure.datasource.repository

import com.skramMaster.domain.model.Article
import com.skramMaster.domain.repository.ArticleRepository
import com.skramMaster.infrastructure.datasource.dao.article.ArticleDao

class ArticleRepositoryImpl : ArticleRepository {
    override fun getArticles(): List<Article> {
        return ArticleDao.all().map { it.toArticle() }
    }

    override fun createArticle(article: Article): Article {
        return ArticleDao.new {
            title = article.title
            content = article.content
        }.toArticle()
    }
}

private fun ArticleDao.toArticle(): Article {
    return Article(
        title = title,
        content = content
    )
}
