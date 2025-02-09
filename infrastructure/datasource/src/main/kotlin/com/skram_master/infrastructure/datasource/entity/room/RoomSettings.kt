package com.skram_master.infrastructure.datasource.entity.room

import com.skram_master.infrastructure.datasource.entity.TimestampIntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

internal object RoomSettings : TimestampIntIdTable("RoomSettings") {
    val voteMethod = reference(
        name = "vote_method",
        foreign = VoteMethods,
        onDelete = ReferenceOption.CASCADE,
    ).uniqueIndex()
}
