package com.skram_master.infrastructure.datasource.dao.room

import com.skram_master.infrastructure.datasource.dao.TimestampIdEntity
import com.skram_master.infrastructure.datasource.entity.room.RoomSettings
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

internal class RoomSettingsDAO(id: EntityID<Int>) : TimestampIdEntity(id, RoomSettings) {
    val voteMethod by CustomVoteMethodDAO.referrersOn(RoomSettings.voteMethod)

    companion object : IntEntityClass<RoomSettingsDAO>(RoomSettings)
}
