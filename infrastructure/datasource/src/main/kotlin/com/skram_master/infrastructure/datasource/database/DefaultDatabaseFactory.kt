package com.skram_master.infrastructure.datasource.database

import com.skram_master.infrastructure.datasource.entity.room.CustomVoteMethodsTable
import com.skram_master.infrastructure.datasource.entity.room.RoomSettingsTable
import com.skram_master.infrastructure.datasource.entity.room.RoomsTable
import com.skram_master.infrastructure.datasource.entity.user.UsersTable
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ExperimentalDatabaseMigrationApi
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.nio.file.Paths

class DefaultDatabaseFactory(
    val user: String,
    val password: String,
    val url: String,
    val migrationsDirectory: String,
) : DatabaseFactory {
    private val migrationsTargetDirectory =
        Paths.get("").toAbsolutePath().toString().replace("ktorapp", migrationsDirectory)
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
            .driver("org.postgresql.Driver")
            .locations("filesystem:$migrationsTargetDirectory")
            .baselineOnMigrate(true) // Used when migrating an existing database for the first time
            .load()
    }

    override fun init() {
        transaction(database) {
            SchemaUtils.create(UsersTable, RoomsTable, RoomSettingsTable, CustomVoteMethodsTable)
        }
    }

    @OptIn(ExperimentalDatabaseMigrationApi::class)
    override fun migrate() {
        transaction(database) {
//            MigrationUtils.generateMigrationScript(
//                *tables,
//                scriptDirectory = migrationsTargetDirectory,
//                scriptName = "fix_type_index",
//            )
        }

        transaction(database) {
            flyway.migrate()
        }
    }

    override fun seed() {
        transaction(database) {
        }
    }
}
