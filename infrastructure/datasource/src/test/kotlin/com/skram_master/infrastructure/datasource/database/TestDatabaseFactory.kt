package com.skram_master.infrastructure.datasource.database

import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction

const val TestUser = "testuser"
const val TestPassword = "testpassword"
const val TestUrl = "jdbc:postgresql://localhost:3241/skram-master-test-db"
const val MigrationsTargetDirectory = "migrationsDirectory"

class TestDatabaseFactory(
    val initBlock: Transaction.() -> Unit = {},
    val migrateBlock: Transaction.() -> Unit = {},
    val seedBlock: Transaction.() -> Unit = {},
    val resetBlock: Transaction.() -> Unit = {},
) : DatabaseFactory {
    override val database: Database by lazy {
        Database.connect(
            url = TestUrl,
            driver = "org.postgresql.Driver",
            user = TestUser,
            password = TestPassword,
        )
    }

    override val flyway: Flyway by lazy {
        Flyway.configure()
            .dataSource(TestUrl, TestUser, TestPassword)
            .driver("org.postgresql.Driver")
            .locations("filesystem:$MigrationsTargetDirectory")
            .load()
    }

    override fun init() {
        transaction(database) {
            initBlock.invoke(this)
        }
    }

    override fun migrate() {
        transaction(database) {
            migrateBlock.invoke(this)
        }
    }

    override fun seed() {
        transaction(database) {
            seedBlock.invoke(this)
        }
    }

    fun reset() {
        transaction(database) {
            resetBlock.invoke(this)
        }
    }
}
