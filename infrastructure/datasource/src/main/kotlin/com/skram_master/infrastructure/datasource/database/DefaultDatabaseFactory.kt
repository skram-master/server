package com.skram_master.infrastructure.datasource.database

import MigrationUtils
import com.skram_master.infrastructure.datasource.entity.article.Articles
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ExperimentalDatabaseMigrationApi
import org.jetbrains.exposed.sql.transactions.transaction

class DefaultDatabaseFactory(
    val user: String,
    val password: String,
    val url: String,
    val migrationsDirectory: String,
) : DatabaseFactory {
    override val database: Database by lazy {
        Database.connect(
            url = url,
            driver = "org.postgresql.Driver",
            user = user,
            password = password,
        )
    }

    override val flyway: Flyway by lazy {
        Flyway.configure()
            .dataSource(url, user, password)
            .locations("filesystem:$migrationsDirectory")
            .load()
    }

    override fun init() {
        transaction(database) {
        }
    }

    @OptIn(ExperimentalDatabaseMigrationApi::class)
    override fun migrate() {
        transaction(database) {
            MigrationUtils.generateMigrationScript(
                Articles,
                scriptDirectory = migrationsDirectory,
                scriptName = "Add article table",
            )
        }
        transaction(database) {
            flyway.migrate()
        }
    }
}
