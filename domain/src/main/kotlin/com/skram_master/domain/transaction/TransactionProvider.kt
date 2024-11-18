package com.skram_master.domain.transaction

interface TransactionProvider {
    suspend fun <T> transaction(block: suspend () -> T): T
}
