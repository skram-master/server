package com.skram_master.domain.repository.vote_method

import com.skram_master.domain.model.vote_method.VoteMethod

interface VoteMethodRepository {
    suspend fun getDefaultVoteMethodList(): List<VoteMethod>
}
