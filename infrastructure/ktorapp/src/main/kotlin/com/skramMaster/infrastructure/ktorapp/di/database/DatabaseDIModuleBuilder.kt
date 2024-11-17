package com.skramMaster.infrastructure.ktorapp.di.database

import com.skramMaster.domain.transaction.TransactionProvider
import com.skramMaster.infrastructure.datasource.database.DatabaseFactory
import com.skramMaster.infrastructure.datasource.database.DefaultDatabaseFactory
import com.skramMaster.infrastructure.datasource.database.DefaultTransactionProvider
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
