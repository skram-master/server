package com.skram_master.infrastructure.datasource.entity.utils

import com.skram_master.core.coroutine.CoroutineProvider
import com.skram_master.infrastructure.datasource.database.DefaultTransactionProvider
import com.skram_master.infrastructure.datasource.database.TestDatabaseFactory
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

@OptIn(ExperimentalCoroutinesApi::class)
internal class ExtendUpdateTest : ShouldSpec({

    val tables = arrayOf(TestIntTable, TestLongTable, TestUUIDTable)
    val testDispatcher = StandardTestDispatcher()
    val databaseFactory = TestDatabaseFactory(
        initBlock = { SchemaUtils.create(*tables) },
        resetBlock = { SchemaUtils.drop(*tables) },
    )
    val transactionProvider = DefaultTransactionProvider(databaseFactory)

    beforeSpec { spec ->
        CoroutineProvider.IO.dispatcherHandler = { testDispatcher }
        spec.coroutineTestScope = true
        databaseFactory.reset()
        databaseFactory.init()
    }

    afterSpec { spec ->
        CoroutineProvider.IO.reset()
    }

    context("custom update* function updates the updatedAt column when it is called") {
        should("bodyWithUpdateAt should update the updatedAt column") {
            // given
            val testIntTable = transactionProvider.transaction {
                TestIntTable.insert {
                    it[name] = "test"
                }
            }.await()

            // when
            val updatedId = transactionProvider.transaction {
                TestIntTable.updateWithUpdatedAt(
                    where = { TestIntTable.id eq testIntTable[TestIntTable.id] },
                    body = { statement ->
                        statement[name] = "updated"
                    },
                )
            }.await()

            // then
            val updatedRecord = transactionProvider.transaction {
                TestIntTable.selectAll().where { TestIntTable.id eq updatedId }.single()
            }.await()

            updatedRecord[TestIntTable.updatedAt] shouldBeGreaterThan testIntTable[TestIntTable.updatedAt]
        }

        should("updateReturningWithUpdatedAt should update the updatedAt column") {
            // given
            val testLongTable = transactionProvider.transaction {
                TestLongTable.insert {
                    it[name] = "test"
                }
            }.await()

            // when
            val updatedAtOfUpdateRecord = transactionProvider.transaction {
                TestLongTable.updateReturningWithUpdatedAt(
                    returning = listOf(TestLongTable.updatedAt),
                    where = { TestLongTable.id eq testLongTable[TestLongTable.id] },
                    body = { statement ->
                        statement[name] = "updated"
                    },
                ).single()
            }.await()

            // then
            updatedAtOfUpdateRecord[TestLongTable.updatedAt] shouldBeGreaterThan testLongTable[TestLongTable.updatedAt]
        }

        should("updateReturningWithUpdatedAt excluded where should update the updatedAt column") {
            // given
            val testUUIDTable = transactionProvider.transaction {
                TestUUIDTable.insert {
                    it[name] = "test"
                }
            }.await()

            // when
            val updatedAtOfUpdateRecord = transactionProvider.transaction {
                TestUUIDTable.updateReturningWithUpdatedAt(
                    returning = listOf(TestUUIDTable.updatedAt),
                    body = { statement ->
                        statement[name] = "updated"
                    },
                ).single()
            }.await()

            // then
            updatedAtOfUpdateRecord[TestUUIDTable.updatedAt] shouldBeGreaterThan testUUIDTable[TestUUIDTable.updatedAt]
        }
    }
})
