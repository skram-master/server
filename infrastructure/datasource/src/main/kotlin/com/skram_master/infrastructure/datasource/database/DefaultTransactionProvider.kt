package com.skram_master.infrastructure.datasource.database

import com.skram_master.domain.transaction.TransactionProvider
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync

class DefaultTransactionProvider(private val databaseFactory: DatabaseFactory) :
    TransactionProvider {
    override suspend fun <T> transaction(block: suspend () -> T): Deferred<T> =
        suspendedTransactionAsync(
            context = Dispatchers.IO,
            db = databaseFactory.database,
            transactionIsolation = null,
        ) { block() }
}
