package com.skramMaster.domain.transaction

interface TransactionProvider {
    suspend fun <T> transaction(block: suspend () -> T): T
}