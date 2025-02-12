package com.skram_master.infrastructure.datasource.repository.vote_method

import com.skram_master.domain.model.vote_method.VoteMethod
import com.skram_master.domain.model.vote_method.VoteMethodOption
import com.skram_master.domain.repository.vote_method.VoteMethodRepository
import com.skram_master.infrastructure.datasource.constrains.vote_method.DefaultVoteMethod

class VoteMethodRepositoryImpl : VoteMethodRepository {
    override suspend fun getDefaultVoteMethodList(): List<VoteMethod> {
        return DefaultVoteMethod.all().map(DefaultVoteMethod::toVoteMethod)
    }
}

private fun DefaultVoteMethod.toVoteMethod(): VoteMethod {
    return VoteMethod(
        type = type,
        options = options
            .map {
                VoteMethodOption(
                    value = it.value,
                    order = it.order,
                )
            },
    )
}
