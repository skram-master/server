package com.skramMaster.resource.article

import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/articles")
class ArticlesV1 {

    @Serializable
    @Resource("/new")
    class New(val parent: ArticlesV1 = ArticlesV1())

    companion object {
        val tags = listOf("Articles")
    }
}