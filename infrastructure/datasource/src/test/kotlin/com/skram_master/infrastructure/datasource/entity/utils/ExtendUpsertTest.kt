package com.skram_master.infrastructure.datasource.entity.utils

import com.skram_master.core.coroutine.CoroutineProvider
import com.skram_master.infrastructure.datasource.database.DefaultTransactionProvider
import com.skram_master.infrastructure.datasource.database.TestDatabaseFactory
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

@OptIn(ExperimentalCoroutinesApi::class)
internal class ExtendUpsertTest : ShouldSpec({

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

    context("custom upsert* function updates the updatedAt column when updating a record") {
        should("upsertWithUpdatedAt should update the updatedAt column") {
            // given
            val testIntTable = transactionProvider.transaction {
                TestIntTable.insert {
                    it[name] = "test"
                }
            }.await()

            // when
            val updatedId = transactionProvider.transaction {
                TestIntTable.upsertWithUpdatedAt(
                    where = { TestIntTable.id eq testIntTable[TestIntTable.id] },
                    body = { statement ->
                        statement[name] = "updated"
                    },
                )
            }.await()

            // then
            val updatedRecord = transactionProvider.transaction {
                TestIntTable.selectAll().where { TestIntTable.id eq updatedId[TestIntTable.id] }
                    .single()
            }.await()

            updatedRecord[TestIntTable.updatedAt] shouldBeGreaterThan testIntTable[TestIntTable.updatedAt]
        }

        should("upsertWithUpdatedAt should update the updatedAt column even if it is UUID table") {
            // given
            val testUUIDTable = transactionProvider.transaction {
                TestUUIDTable.insert {
                    it[name] = "test"
                }
            }.await()

            // when
            val updatedId = transactionProvider.transaction {
                TestUUIDTable.upsertWithUpdatedAt(
                    where = { TestUUIDTable.id eq testUUIDTable[TestUUIDTable.id] },
                    body = { statement ->
                        statement[name] = "updated"
                    },
                )
            }.await()

            // then
            val updatedRecord = transactionProvider.transaction {
                TestUUIDTable.selectAll().where { TestUUIDTable.id eq updatedId[TestUUIDTable.id] }
                    .single()
            }.await()

            updatedRecord[TestUUIDTable.updatedAt] shouldBeGreaterThan testUUIDTable[TestUUIDTable.updatedAt]
        }

        should("upsertReturningWithUpdatedAt should update the updatedAt column") {
            // given
            val testLongTable = transactionProvider.transaction {
                TestLongTable.insert {
                    it[name] = "test"
                }
            }.await()

            // when
            val updatedAtOfUpsertRecord = transactionProvider.transaction {
                TestLongTable.upsertReturningWithUpdatedAt(
                    where = { TestLongTable.id eq testLongTable[TestLongTable.id] },
                    body = { statement ->
                        statement[name] = "updated"
                    },
                ).single()
            }.await()

            // then
            updatedAtOfUpsertRecord[TestLongTable.updatedAt] shouldBeGreaterThan testLongTable[TestLongTable.updatedAt]
        }

        should("batchUpsertWithUpdatedAt should update all of the updatedAt column") {
            // given
            val range = 1..10
            val testUUIDTables = transactionProvider.transaction {
                TestUUIDTable.batchInsert(range) { index ->
                    this[TestUUIDTable.name] = "test$index"
                }
            }.await()

            // when
            val updatedAtOfUpsertRecords = transactionProvider.transaction {
                TestUUIDTable.batchUpsertWithUpdatedAt(
                    data = testUUIDTables,
                    keys = arrayOf(TestUUIDTable.id),
                    body = { _ ->
                        this[TestUUIDTable.name] = "updated"
                    },
                )
            }.await()

            // then
            testUUIDTables.forEachIndexed { index, testUUIDTable ->
                val updatedAtOfUpsertRecord = updatedAtOfUpsertRecords[index]
                updatedAtOfUpsertRecord[TestUUIDTable.updatedAt] shouldBeGreaterThan
                    testUUIDTable[TestUUIDTable.updatedAt]
            }
        }

        should("batchUpsertWithUpdatedAt should update all of the updatedAt column even if data is sequence") {
            // given
            val range = 1..10
            val testLongTables = transactionProvider.transaction {
                TestLongTable.batchInsert(range) { index ->
                    this[TestLongTable.name] = "test$index"
                }
            }.await()

            val testSequenceLongTables = sequence { testLongTables.forEach { yield(it) } }

            // when
            val updatedAtOfUpsertRecords = transactionProvider.transaction {
                TestLongTable.batchUpsertWithUpdatedAt(
                    data = testSequenceLongTables,
                    keys = arrayOf(TestLongTable.id),
                    body = { _ ->
                        this[TestLongTable.name] = "updated"
                    },
                )
            }.await()

            // then
            testLongTables.forEachIndexed { index, testLongTable ->
                val updatedAtOfUpsertRecord = updatedAtOfUpsertRecords[index]
                updatedAtOfUpsertRecord[TestLongTable.updatedAt] shouldBeGreaterThan
                    testLongTable[TestLongTable.updatedAt]
            }
        }
    }
})
