package com.skram_master.infrastructure.datasource.entity.room

import com.skram_master.infrastructure.datasource.entity.user.UsersTable
import com.skram_master.infrastructure.datasource.entity.utils.TimestampUUIDTable
import org.jetbrains.exposed.sql.ReferenceOption

internal object RoomsTable : TimestampUUIDTable("Rooms") {
    val name = varchar("name", 255).nullable()
    val roomOwner = reference(
        name = "room_owner",
        foreign = UsersTable,
        onDelete = ReferenceOption.SET_NULL,
    )
    val roomSettings = reference(
        name = "room_settings",
        foreign = RoomSettingsTable,
        onDelete = ReferenceOption.CASCADE,
    ).uniqueIndex()
}
