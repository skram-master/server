package com.skram_master.infrastructure.datasource.database

import com.skram_master.core.coroutine.CoroutineProvider
import com.skram_master.domain.transaction.TransactionProvider
import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync

class DefaultTransactionProvider(private val databaseFactory: DatabaseFactory) :
    TransactionProvider {
    override suspend fun <T> transaction(block: suspend () -> T): Deferred<T> =
        suspendedTransactionAsync(
            context = CoroutineProvider.IO.dispatcher,
            db = databaseFactory.database,
            transactionIsolation = null,
        ) { block() }
}
