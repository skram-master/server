package com.skramMaster.infrastructure.datasource.database

import kotlinx.coroutines.Deferred
import org.jetbrains.exposed.sql.Database

interface DatabaseFactory {
    val database: Database

    fun init()

    suspend fun <T> dbQuery(block: suspend () -> T): Deferred<T>
}
