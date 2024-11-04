package com.skramMaster.infrastructure.datasource.entity.article

import com.skramMaster.infrastructure.datasource.entity.BaseSchemaUtils
import org.jetbrains.exposed.sql.SchemaUtils

object ArticleSchemaUtils : BaseSchemaUtils {
    override fun create() {
        SchemaUtils.createMissingTablesAndColumns(Articles)
    }
}