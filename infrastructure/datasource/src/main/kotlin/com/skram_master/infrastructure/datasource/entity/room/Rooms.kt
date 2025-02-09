package com.skram_master.infrastructure.datasource.entity.room

import com.skram_master.infrastructure.datasource.entity.TimestampUUIDTable
import com.skram_master.infrastructure.datasource.entity.user.Users
import org.jetbrains.exposed.sql.ReferenceOption

internal object Rooms : TimestampUUIDTable("Rooms") {
    val name = varchar("name", 255).nullable()
    val roomOwner = reference(
        name = "room_owner",
        foreign = Users,
        onDelete = ReferenceOption.SET_NULL,
    )
    val roomSettings = reference(
        name = "room_settings",
        foreign = RoomSettings,
        onDelete = ReferenceOption.CASCADE,
    ).uniqueIndex()
}
