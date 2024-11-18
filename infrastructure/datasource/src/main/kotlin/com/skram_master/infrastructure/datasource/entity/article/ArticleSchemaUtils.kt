package com.skram_master.infrastructure.datasource.entity.article

import com.skram_master.infrastructure.datasource.entity.BaseSchemaUtils
import org.jetbrains.exposed.sql.SchemaUtils

object ArticleSchemaUtils : BaseSchemaUtils {
    override fun create() {
        SchemaUtils.createMissingTablesAndColumns(Articles)
    }
}
