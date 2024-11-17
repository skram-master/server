package com.skramMaster.infrastructure.datasource.dao.article

import com.skramMaster.infrastructure.datasource.dao.TimestampIdEntity
import com.skramMaster.infrastructure.datasource.entity.article.Articles
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

internal class ArticleDao(id: EntityID<Int>) : TimestampIdEntity(id, Articles) {
    var title by Articles.title
    var content by Articles.content

    companion object : IntEntityClass<ArticleDao>(Articles)
}
