package com.skram_master.infrastructure.datasource.entity.room

import com.skram_master.infrastructure.datasource.entity.TimestampUUIDTable
import com.skram_master.infrastructure.datasource.entity.user.Users

internal object Rooms : TimestampUUIDTable("Rooms") {
    val name = varchar("name", 255).nullable()
    val roomOwner = reference("room_owner", Users.id)
    val roomSettings = reference("room_settings", RoomSettings.id)
}
