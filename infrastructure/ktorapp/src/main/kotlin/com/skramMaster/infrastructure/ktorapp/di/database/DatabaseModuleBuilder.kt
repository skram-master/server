package com.skramMaster.infrastructure.ktorapp.di.database

import com.skramMaster.infrastructure.datasource.database.DatabaseFactory
import com.skramMaster.infrastructure.datasource.database.DefaultDatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

object DatabaseModuleBuilder {
    fun module(
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
    }
}