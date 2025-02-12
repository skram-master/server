package com.skram_master.infrastructure.datasource.entity.room

import com.skram_master.enums.VoteMethodType
import com.skram_master.infrastructure.datasource.entity.TimestampIntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

// TODO: add detekt
internal object RoomSettingsTable : TimestampIntIdTable("RoomSettings") {
    val voteMethod = enumeration<VoteMethodType>("vote_method")
    val customVoteMethod = reference(
        name = "custom_vote_method",
        foreign = CustomVoteMethodsTable,
        onDelete = ReferenceOption.CASCADE,
    ).nullable().uniqueIndex()
}
