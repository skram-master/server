package com.skraMaster.dto.article

data class ArticleRequest(
    val title: String,
    val content: String,
)

data class ArticleResponse(
    val id: Int,
    val title: String,
    val content: String,
)