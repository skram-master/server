package com.skram_master.infrastructure.datasource.entity.room

import com.skram_master.enums.VoteMethod.VoteMethodType
import com.skram_master.infrastructure.datasource.common.vote_method.VoteMethodOption
import com.skram_master.infrastructure.datasource.entity.utils.TimestampIntIdTable
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.json.json

internal object CustomVoteMethodsTable : TimestampIntIdTable("CustomVoteMethods") {
    val type = enumeration("type", VoteMethodType::class).index()
    val options = json<Array<VoteMethodOption>>("options", Json.Default)
}
