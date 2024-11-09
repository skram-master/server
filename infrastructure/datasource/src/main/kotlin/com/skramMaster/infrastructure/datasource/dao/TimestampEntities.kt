package com.skramMaster.infrastructure.datasource.dao

import com.skramMaster.infrastructure.datasource.entity.TimestampIntIdTable
import com.skramMaster.infrastructure.datasource.entity.TimestampIntUUIDTable
import com.skramMaster.infrastructure.datasource.entity.TimestampLongIdTable
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.dao.id.EntityID

internal abstract class TimestampIdEntity(id: EntityID<Int>, table: TimestampIntIdTable) : IntEntity(id) {
    val createdAt by table.createdAt
    var updatedAt by table.updatedAt
}

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

internal abstract class TimestampLongEntity(id: EntityID<Long>, table: TimestampLongIdTable) : LongEntity(id) {
    val createdAt by table.createdAt
    var updatedAt by table.updatedAt
}

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

internal abstract class TimestampUUIDEntity(id: EntityID<java.util.UUID>, table: TimestampIntUUIDTable) :
    UUIDEntity(id) {
    val createdAt by table.createdAt
    var updatedAt by table.updatedAt
}

internal abstract class TimestampUUIDEntityClass<E : TimestampUUIDEntity>(table: TimestampIntUUIDTable) :
    UUIDEntityClass<E>(table) {
    init {
        EntityHook.subscribe { action ->
            if (action.changeType == EntityChangeType.Updated) {
                action.toEntity(this)?.updatedAt = Clock.System.now().toLocalDateTime(TimeZone.UTC)
            }
        }
    }
}
