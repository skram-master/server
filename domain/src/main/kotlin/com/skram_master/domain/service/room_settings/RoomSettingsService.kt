package com.skram_master.domain.service.room_settings

import com.skram_master.domain.model.room_settings.RoomSettingsInfo
import com.skram_master.domain.repository.vote_method.VoteMethodRepository
import com.skram_master.domain.transaction.TransactionProvider

class RoomSettingsService(
    val voteMethodRepository: VoteMethodRepository,
    val transactionProvider: TransactionProvider,
) {
    suspend fun getRoomSettingsInfo(): RoomSettingsInfo = transactionProvider.transaction {
        val defaultVoteMethodList = voteMethodRepository.getDefaultVoteMethodList()
        return@transaction RoomSettingsInfo(
            defaultVoteMethodList = defaultVoteMethodList,
        )
    }.await()
}
