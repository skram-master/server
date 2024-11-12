package com.skramMaster.infrastructure.datasource.entity

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

private interface ConvertToSnakeCase {
    fun getTableName(tableName: String): String {
        return tableName.replace(Regex("([a-z])([A-Z])")) {
            "${it.groupValues[1]}_${it.groupValues[2]}"
        }.lowercase()
    }
}

internal abstract class TimestampIntIdTable(tableName: String) : IntIdTable(getTableName(tableName)) {

    val createdAt = datetime("created_at").clientDefault { Clock.System.now().toLocalDateTime(TimeZone.UTC) }
    val updatedAt = datetime("updated_at").clientDefault { Clock.System.now().toLocalDateTime(TimeZone.UTC) }

    companion object : ConvertToSnakeCase
}

internal abstract class TimestampLongIdTable(tableName: String) : LongIdTable(getTableName(tableName)) {

    val createdAt = datetime("created_at").clientDefault { Clock.System.now().toLocalDateTime(TimeZone.UTC) }
    val updatedAt = datetime("updated_at").clientDefault { Clock.System.now().toLocalDateTime(TimeZone.UTC) }

    companion object : ConvertToSnakeCase
}

internal abstract class TimestampIntUUIDTable(tableName: String) : UUIDTable(tableName) {
    val createdAt = datetime("created_at").clientDefault { Clock.System.now().toLocalDateTime(TimeZone.UTC) }
    val updatedAt = datetime("updated_at").clientDefault { Clock.System.now().toLocalDateTime(TimeZone.UTC) }

    companion object : ConvertToSnakeCase
}
