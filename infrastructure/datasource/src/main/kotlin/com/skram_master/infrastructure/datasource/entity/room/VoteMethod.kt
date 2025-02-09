package com.skram_master.infrastructure.datasource.entity.room

import com.skram_master.infrastructure.datasource.entity.TimestampIntIdTable
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.json.json

internal enum class VoteMethodType {
    Fibonacci,
    ModifiedFibonacci,
    TShirtSize,
    PowerOfTwo,
    Custom,
}

@Serializable
internal data class VoteOptions(
    val value: String,
    val order: Int,
)

internal object VoteMethod : TimestampIntIdTable("VoteMethod") {
    val type = enumeration("type", VoteMethodType::class).uniqueIndex()
    val options = json<Array<VoteOptions>>("options", Json.Default)
}
