package com.skram_master.infrastructure.ktorapp.di.database

import com.skram_master.domain.transaction.TransactionProvider
import com.skram_master.infrastructure.datasource.database.DatabaseFactory
import com.skram_master.infrastructure.datasource.database.DefaultDatabaseFactory
import com.skram_master.infrastructure.datasource.database.DefaultTransactionProvider
import org.koin.core.module.Module
import org.koin.dsl.module

object DatabaseDIModuleBuilder {
    fun build(
        name: String,
        user: String,
        password: String,
    ): Module = module {
        single<DatabaseFactory> {
            DefaultDatabaseFactory(
                name = name,
                user = user,
                password = password,
            )
        }
        single<TransactionProvider> { DefaultTransactionProvider(get()) }
    }
}
