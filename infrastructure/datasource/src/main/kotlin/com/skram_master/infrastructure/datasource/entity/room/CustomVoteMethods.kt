package com.skram_master.infrastructure.datasource.entity.room

import com.skram_master.enums.VoteMethodType
import com.skram_master.infrastructure.datasource.common.vote_method.VoteMethodOption
import com.skram_master.infrastructure.datasource.entity.TimestampIntIdTable
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.json.json

internal object CustomVoteMethods : TimestampIntIdTable("CustomVoteMethods") {
    val type = enumeration("type", VoteMethodType::class).index()
    val options = json<Array<VoteMethodOption>>("options", Json.Default)
}
