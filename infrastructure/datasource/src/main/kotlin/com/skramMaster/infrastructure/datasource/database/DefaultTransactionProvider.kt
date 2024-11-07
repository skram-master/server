package com.skramMaster.infrastructure.datasource.database

import com.skramMaster.domain.transaction.TransactionProvider
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync

class DefaultTransactionProvider(private val database: Database) : TransactionProvider {
    override suspend fun <T> transaction(block: suspend () -> T): Deferred<T> = suspendedTransactionAsync(
        context = Dispatchers.IO,
        db = database,
        transactionIsolation = null,
    ) { block() }
}