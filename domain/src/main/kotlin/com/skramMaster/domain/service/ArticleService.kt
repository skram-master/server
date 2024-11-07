package com.skramMaster.domain.service

import com.skramMaster.domain.model.Article
import com.skramMaster.domain.repository.ArticleRepository

interface ArticleService {
    fun getArticles(): List<Article>
    fun createArticle(article: Article): Article
}

class ArticleServiceImpl(
    private val articleRepository: ArticleRepository
) : ArticleService {
    override fun getArticles(): List<Article> = articleRepository.getArticles()

    override fun createArticle(article: Article): Article = articleRepository.createArticle(article)
}