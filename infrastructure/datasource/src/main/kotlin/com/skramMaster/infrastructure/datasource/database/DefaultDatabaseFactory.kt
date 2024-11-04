package com.skramMaster.infrastructure.datasource.database

import com.skramMaster.infrastructure.datasource.entity.article.ArticleSchemaUtils
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import org.jetbrains.exposed.sql.transactions.transaction

class DefaultDatabaseFactory(val name: String, val user: String, val password: String) : DatabaseFactory {
    override val database: Database by lazy {
        Database.connect(
            url = "jdbc:postgresql://db:5432/$name",
            driver = "org.postgresql.Driver",
            user = user,
            password = password
        )
    }


    override fun init() {
        transaction(database) {
            ArticleSchemaUtils.create()
        }
    }

    override suspend fun <T> dbQuery(block: suspend () -> T): Deferred<T> = suspendedTransactionAsync(
        context = Dispatchers.IO,
        db = database,
        transactionIsolation = null,
    ) { block() }
}
