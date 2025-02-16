package com.skram_master.infrastructure.datasource.entity.article

import com.skram_master.infrastructure.datasource.entity.utils.TimestampIntIdTable

internal object ArticlesTable : TimestampIntIdTable("Articles") {
    val title = varchar("title", 255)
    val content = text("content")
}
