package com.skram_master.infrastructure.datasource.entity.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import java.util.*

private fun convertToSnakeCase(tableName: String): String {
    return tableName.replace(Regex("([a-z])([A-Z])")) {
        "${it.groupValues[1]}_${it.groupValues[2]}"
    }.lowercase()
}

internal interface TimestampTables {
    val createdAt: Column<LocalDateTime>
    val updatedAt: Column<LocalDateTime>
}

internal open class TimestampTable<T : Any>(
    tableName: String,
    idColumn: Table.() -> Column<EntityID<T>>,
) : IdTable<T>(convertToSnakeCase(tableName)), TimestampTables {
    override val id: Column<EntityID<T>> = idColumn()
    override val primaryKey = PrimaryKey(id)

    override val createdAt =
        datetime("created_at").clientDefault { Clock.System.now().toLocalDateTime(TimeZone.UTC) }
    override val updatedAt =
        datetime("updated_at").clientDefault { Clock.System.now().toLocalDateTime(TimeZone.UTC) }
}

internal abstract class TimestampIntIdTable(tableName: String) : TimestampTable<Int>(
    tableName = tableName,
    idColumn = { integer("id").autoIncrement().entityId() },
)

internal abstract class TimestampLongIdTable(tableName: String) : TimestampTable<Long>(
    tableName = tableName,
    idColumn = { long("id").autoIncrement().entityId() },
)

internal abstract class TimestampUUIDTable(tableName: String) : TimestampTable<UUID>(
    tableName = tableName,
    idColumn = { uuid("id").autoGenerate().entityId() },
)
