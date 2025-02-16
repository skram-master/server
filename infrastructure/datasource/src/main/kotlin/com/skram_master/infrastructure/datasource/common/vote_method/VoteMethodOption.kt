package com.skram_master.infrastructure.datasource.common.vote_method

import kotlinx.serialization.Serializable

@Serializable
data class VoteMethodOption(
    val value: String,
    val order: Int,
)
