package com.skramMaster.infrastructure.datasource.entity.article

import com.skramMaster.infrastructure.datasource.entity.TimestampIntIdTable

internal object Articles : TimestampIntIdTable("Articles") {
    val title = varchar("title", 255)
    val content = text("content")
}
