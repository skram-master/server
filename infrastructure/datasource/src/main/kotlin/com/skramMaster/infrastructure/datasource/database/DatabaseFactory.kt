package com.skramMaster.infrastructure.datasource.database

import org.jetbrains.exposed.sql.Database

interface DatabaseFactory {
    val database: Database

    fun init()
}
