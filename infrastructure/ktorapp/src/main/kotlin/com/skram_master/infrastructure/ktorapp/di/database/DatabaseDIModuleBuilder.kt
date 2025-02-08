package com.skram_master.infrastructure.ktorapp.di.database

import com.skram_master.domain.transaction.TransactionProvider
import com.skram_master.infrastructure.datasource.database.DatabaseFactory
import com.skram_master.infrastructure.datasource.database.DefaultDatabaseFactory
import com.skram_master.infrastructure.datasource.database.DefaultTransactionProvider
import org.koin.core.module.Module
import org.koin.dsl.module

object DatabaseDIModuleBuilder {
    fun build(
        user: String,
        password: String,
        url: String,
    ): Module = module {
        single<DatabaseFactory> {
            DefaultDatabaseFactory(
                user = user,
                password = password,
                url = url,
            )
        }
        single<TransactionProvider> { DefaultTransactionProvider(get()) }
    }
}
