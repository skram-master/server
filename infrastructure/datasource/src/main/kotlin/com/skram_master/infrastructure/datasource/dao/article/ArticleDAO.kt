package com.skram_master.infrastructure.datasource.dao.article

import com.skram_master.infrastructure.datasource.dao.TimestampIntIdEntity
import com.skram_master.infrastructure.datasource.dao.TimestampIntIdEntityClass
import com.skram_master.infrastructure.datasource.entity.article.ArticlesTable
import org.jetbrains.exposed.dao.id.EntityID

internal class ArticleDAO(id: EntityID<Int>) : TimestampIntIdEntity(id, ArticlesTable) {
    var title by ArticlesTable.title
    var content by ArticlesTable.content

    companion object : TimestampIntIdEntityClass<ArticleDAO>(ArticlesTable)
}
