package com.skram_master.infrastructure.datasource.repository.vote_method

import com.skram_master.domain.model.vote_method.VoteMethod
import com.skram_master.domain.model.vote_method.VoteMethodOption
import com.skram_master.domain.repository.vote_method.VoteMethodRepository
import com.skram_master.enums.VoteMethodType
import com.skram_master.infrastructure.datasource.dao.room.CustomVoteMethodDAO
import com.skram_master.infrastructure.datasource.entity.room.CustomVoteMethods

class VoteMethodRepositoryImpl : VoteMethodRepository {
    override suspend fun getDefaultVoteMethodList(): List<VoteMethod> {
        val defaultVoteMethodList = CustomVoteMethodDAO
            .find {
                CustomVoteMethods.type neq VoteMethodType.Custom
            }.map { it.toVoteMethod() }
            .sortedBy(VoteMethod::type)
        return defaultVoteMethodList
    }
}

private fun CustomVoteMethodDAO.toVoteMethod(): VoteMethod {
    return VoteMethod(
        type = type,
        options = options
            .map {
                VoteMethodOption(
                    value = it.value,
                    order = it.order,
                )
            }.sortedBy(VoteMethodOption::order),
    )
}
