package com.skram_master.infrastructure.datasource.database

import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database

interface DatabaseFactory {
    val database: Database
    val flyway: Flyway

    fun init()

    fun migrate()
}
