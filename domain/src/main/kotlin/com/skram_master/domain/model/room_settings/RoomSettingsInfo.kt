package com.skram_master.domain.model.room_settings

import com.skram_master.domain.model.vote_method.VoteMethod

data class RoomSettingsInfo(
    val defaultVoteMethodList: List<VoteMethod>,
)
