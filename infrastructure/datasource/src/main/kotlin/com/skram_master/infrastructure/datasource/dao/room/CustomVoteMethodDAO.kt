package com.skram_master.infrastructure.datasource.dao.room

import com.skram_master.infrastructure.datasource.dao.TimestampIdEntity
import com.skram_master.infrastructure.datasource.entity.room.CustomVoteMethods
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

internal class CustomVoteMethodDAO(id: EntityID<Int>) : TimestampIdEntity(id, CustomVoteMethods) {
    var type by CustomVoteMethods.type
    var options by CustomVoteMethods.options

    companion object : IntEntityClass<CustomVoteMethodDAO>(CustomVoteMethods)
}
