package com.skram_master.infrastructure.datasource.database

import com.skram_master.infrastructure.datasource.entity.article.ArticleSchemaUtils
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class DefaultDatabaseFactory(
    val user: String,
    val password: String,
    val url: String,
) : DatabaseFactory {
    override val database: Database by lazy {
        Database.connect(
            url = url,
            driver = "org.postgresql.Driver",
            user = user,
            password = password,
        )
    }

    override fun init() {
        transaction(database) {
            ArticleSchemaUtils.create()
        }
    }
}
