package com.skramMaster.infrastructure.datasource.database

import com.skramMaster.domain.transaction.TransactionProvider
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync

class DefaultTransactionProvider(private val databaseFactory: DatabaseFactory) : TransactionProvider {
    override suspend fun <T> transaction(block: suspend () -> T): T = suspendedTransactionAsync(
        context = Dispatchers.IO,
        db = databaseFactory.database,
        transactionIsolation = null,
    ) { block() }.await()
}
