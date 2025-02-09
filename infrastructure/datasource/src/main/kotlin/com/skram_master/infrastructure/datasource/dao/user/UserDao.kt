package com.skram_master.infrastructure.datasource.dao.user

import com.skram_master.infrastructure.datasource.dao.TimestampUUIDEntity
import com.skram_master.infrastructure.datasource.entity.user.Users
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

internal class UserDao(id: EntityID<UUID>) : TimestampUUIDEntity(id, Users) {
    var name by Users.name

    companion object : UUIDEntityClass<UserDao>(Users)
}
