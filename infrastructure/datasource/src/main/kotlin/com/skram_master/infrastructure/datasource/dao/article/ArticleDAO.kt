package com.skram_master.infrastructure.datasource.dao.article

import com.skram_master.infrastructure.datasource.dao.TimestampIntIdEntity
import com.skram_master.infrastructure.datasource.dao.TimestampIntIdEntityClass
import com.skram_master.infrastructure.datasource.entity.article.Articles
import org.jetbrains.exposed.dao.id.EntityID

internal class ArticleDAO(id: EntityID<Int>) : TimestampIntIdEntity(id, Articles) {
    var title by Articles.title
    var content by Articles.content

    companion object : TimestampIntIdEntityClass<ArticleDAO>(Articles)
}
