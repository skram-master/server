package com.skram_master.infrastructure.ktorapp.plugin

import com.skram_master.infrastructure.datasource.database.DatabaseFactory
import io.ktor.server.application.Application
import org.koin.ktor.ext.inject

fun Application.configureDatabase() {
    val databaseFactory: DatabaseFactory by inject()

    databaseFactory.init()
}
