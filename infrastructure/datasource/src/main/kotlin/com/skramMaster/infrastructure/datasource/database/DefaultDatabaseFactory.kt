package com.skramMaster.infrastructure.datasource.database

import com.skramMaster.infrastructure.datasource.entity.article.ArticleSchemaUtils
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction


class DefaultDatabaseFactory(val name: String, val user: String, val password: String) : DatabaseFactory {
    override val database: Database by lazy {
        Database.connect(
            url = "jdbc:postgresql://db:5432/$name",
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
