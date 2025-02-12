package com.skram_master.infrastructure.datasource.entity.user

import com.skram_master.infrastructure.datasource.entity.utils.TimestampUUIDTable

internal object UsersTable : TimestampUUIDTable("Users") {
    val name = varchar("name", 255)
}
