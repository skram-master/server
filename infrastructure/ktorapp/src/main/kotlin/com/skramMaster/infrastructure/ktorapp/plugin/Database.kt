package com.skramMaster.infrastructure.ktorapp.plugin

import com.skramMaster.infrastructure.datasource.database.DatabaseFactory
import io.ktor.server.application.Application
import org.koin.ktor.ext.inject

fun Application.configureDatabase() {
    val databaseFactory: DatabaseFactory by inject()

    databaseFactory.init()
}
