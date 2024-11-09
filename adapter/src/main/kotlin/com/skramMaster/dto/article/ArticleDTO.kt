package com.skramMaster.dto.article

import kotlinx.serialization.Serializable

@Serializable
data class ArticleGetV1Request(
    val title: String,
    val content: String,
)

@Serializable
data class ArticleV1Response(
    val id: Int,
    val title: String,
    val content: String,
)