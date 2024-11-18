package com.skram_master.domain.service

import com.skram_master.domain.model.Article
import com.skram_master.domain.repository.ArticleRepository
import com.skram_master.domain.transaction.TransactionProvider

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
