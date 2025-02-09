package com.skram_master.infrastructure.datasource.dao

import com.skram_master.infrastructure.datasource.entity.TimestampIntIdTable
import com.skram_master.infrastructure.datasource.entity.TimestampLongIdTable
import com.skram_master.infrastructure.datasource.entity.TimestampUUIDTable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.EntityChangeType
import org.jetbrains.exposed.dao.EntityHook
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.toEntity

internal abstract class TimestampIdEntity(id: EntityID<Int>, table: TimestampIntIdTable) :
    IntEntity(id) {
    @Suppress("Unused")
    val createdAt by table.createdAt
    var updatedAt by table.updatedAt
}

@Suppress("Unused")
internal abstract class TimestampIdEntityClass<E : TimestampIdEntity>(table: TimestampIntIdTable) :
    IntEntityClass<E>(table) {
    init {
        EntityHook.subscribe { action ->
            if (action.changeType == EntityChangeType.Updated) {
                action.toEntity(this)?.updatedAt = Clock.System.now().toLocalDateTime(TimeZone.UTC)
            }
        }
    }
}

internal abstract class TimestampLongEntity(id: EntityID<Long>, table: TimestampLongIdTable) :
    LongEntity(id) {
    @Suppress("Unused")
    val createdAt by table.createdAt
    var updatedAt by table.updatedAt
}

@Suppress("Unused")
internal abstract class TimestampLongEntityClass<E : TimestampLongEntity>(table: TimestampLongIdTable) :
    LongEntityClass<E>(table) {
    init {
        EntityHook.subscribe { action ->
            if (action.changeType == EntityChangeType.Updated) {
                action.toEntity(this)?.updatedAt = Clock.System.now().toLocalDateTime(TimeZone.UTC)
            }
        }
    }
}

internal abstract class TimestampUUIDEntity(
    id: EntityID<java.util.UUID>,
    table: TimestampUUIDTable,
) :
    UUIDEntity(id) {
    @Suppress("Unused")
    val createdAt by table.createdAt
    var updatedAt by table.updatedAt
}

@Suppress("Unused")
internal abstract class TimestampUUIDEntityClass<E : TimestampUUIDEntity>(table: TimestampUUIDTable) :
    UUIDEntityClass<E>(table) {
    init {
        EntityHook.subscribe { action ->
            if (action.changeType == EntityChangeType.Updated) {
                action.toEntity(this)?.updatedAt = Clock.System.now().toLocalDateTime(TimeZone.UTC)
            }
        }
    }
}
