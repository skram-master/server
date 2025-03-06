package com.skram_master.domain.model.vote_method

import com.skram_master.enums.vote_method.VoteMethodType

data class VoteMethodOption(
    val value: String,
    val order: Int,
)

data class VoteMethod(
    val type: VoteMethodType,
    val options: List<VoteMethodOption>,
)
