package com.skram_master.controller.room_settings

import com.skram_master.domain.model.room_settings.RoomSettingsInfo
import com.skram_master.domain.service.room_settings.RoomSettingsService
import com.skram_master.dto.room_settings.GetRoomSettingsInfoV1Response
import com.skram_master.dto.vote_method.VoteMethodDTO

class RoomSettingsController(
    val roomSettingsService: RoomSettingsService,
) {
    suspend fun getRoomSettingsInfo(): GetRoomSettingsInfoV1Response {
        val roomSettingsInfo = roomSettingsService.getRoomSettingsInfo()
        return roomSettingsInfo.toGetRoomSettingsInfoV1Response()
    }
}

private fun RoomSettingsInfo.toGetRoomSettingsInfoV1Response(): GetRoomSettingsInfoV1Response {
    return GetRoomSettingsInfoV1Response(
        voteMethodList = defaultVoteMethodList.map { defaultVoteMethod ->
            VoteMethodDTO(
                type = defaultVoteMethod.type,
                options = defaultVoteMethod.options.map { it.value },
            )
        },
    )
}
