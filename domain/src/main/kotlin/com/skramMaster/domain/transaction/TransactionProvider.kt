package com.skramMaster.domain.transaction

import kotlinx.coroutines.Deferred

interface TransactionProvider {
    suspend fun <T> transaction(block: suspend () -> T): Deferred<T>
}