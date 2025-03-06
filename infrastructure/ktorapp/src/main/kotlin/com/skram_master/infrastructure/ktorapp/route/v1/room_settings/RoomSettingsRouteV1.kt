package com.skram_master.infrastructure.ktorapp.route.v1.room_settings

import com.skram_master.controller.room_settings.RoomSettingsController
import com.skram_master.dto.room_settings.GetRoomSettingsInfoV1Response
import com.skram_master.dto.vote_method.VoteMethodDTO
import com.skram_master.enums.VoteMethod.VoteMethodType
import com.skram_master.infrastructure.ktorapp.extension.get
import com.skram_master.resource.room_settings.RoomSettingsV1
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route

fun Route.roomSettingsRouteV1(roomSettingsController: RoomSettingsController) {
    getRoomSettingsInfo(roomSettingsController = roomSettingsController)
}

private fun Route.getRoomSettingsInfo(roomSettingsController: RoomSettingsController) {
    get<RoomSettingsV1>(
        {
            tags = RoomSettingsV1.tags
            response {
                HttpStatusCode.OK to {
                    description = "Successful Request"
                    body<List<GetRoomSettingsInfoV1Response>> {
                        example("default") {
                            value = GetRoomSettingsInfoV1Response(
                                voteMethodList = listOf(
                                    VoteMethodDTO(
                                        type = VoteMethodType.Fibonacci,
                                        options = listOf("0", "1", "2", "3", "5"),
                                    ),
                                    VoteMethodDTO(
                                        type = VoteMethodType.TShirtSize,
                                        options = listOf("XS", "S", "M", "L", "XL"),
                                    ),
                                ),
                            )
                        }
                    }
                }
                HttpStatusCode.InternalServerError to {
                    description = "Something unexpected happened"
                }
            }
        },
    ) {
        call.respond(roomSettingsController.getRoomSettingsInfo())
    }
}
