package com.skram_master.infrastructure.datasource.dao.room

import com.skram_master.infrastructure.datasource.dao.TimestampIdEntity
import com.skram_master.infrastructure.datasource.entity.room.VoteMethods
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

internal class VoteMethodDAO(id: EntityID<Int>) : TimestampIdEntity(id, VoteMethods) {
    var type by VoteMethods.type
    var options by VoteMethods.options

    companion object : IntEntityClass<VoteMethodDAO>(VoteMethods)
}
