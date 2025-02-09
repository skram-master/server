package com.skram_master.infrastructure.datasource.dao.room

import com.skram_master.infrastructure.datasource.dao.TimestampUUIDEntity
import com.skram_master.infrastructure.datasource.entity.room.Rooms
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

internal class RoomDao(id: EntityID<UUID>) : TimestampUUIDEntity(id, Rooms) {
    var name by Rooms.name
    var roomOwner by Rooms.roomOwner
    val roomSettings by RoomSettingsDao.referrersOn(Rooms.roomSettings)

    companion object : UUIDEntityClass<RoomDao>(Rooms)
}
