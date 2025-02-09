package com.skram_master.infrastructure.datasource.dao.article

import com.skram_master.infrastructure.datasource.dao.TimestampIdEntity
import com.skram_master.infrastructure.datasource.entity.article.Articles
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

internal class ArticleDAO(id: EntityID<Int>) : TimestampIdEntity(id, Articles) {
    var title by Articles.title
    var content by Articles.content

    companion object : IntEntityClass<ArticleDAO>(Articles)
}
