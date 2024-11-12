package com.skramMaster.domain.service

import com.skramMaster.domain.model.Article
import com.skramMaster.domain.repository.ArticleRepository
import com.skramMaster.domain.transaction.TransactionProvider

interface ArticleService {
    suspend fun getArticles(): List<Article>
    suspend fun createArticle(article: Article): Article
}

class ArticleServiceImpl(
    private val transactionProvider: TransactionProvider,
    private val articleRepository: ArticleRepository,
) : ArticleService {
    override suspend fun getArticles(): List<Article> = transactionProvider.transaction {
        articleRepository.getArticles()
    }

    override suspend fun createArticle(article: Article): Article = transactionProvider.transaction {
        articleRepository.createArticle(article)
    }
}
