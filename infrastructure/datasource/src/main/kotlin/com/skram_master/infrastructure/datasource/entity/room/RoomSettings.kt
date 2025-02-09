package com.skram_master.infrastructure.datasource.entity.room

import com.skram_master.infrastructure.datasource.entity.TimestampIntIdTable

internal object RoomSettings : TimestampIntIdTable("RoomSettings") {
    val voteMethod = reference("vote_method", VoteMethod.id)
}
