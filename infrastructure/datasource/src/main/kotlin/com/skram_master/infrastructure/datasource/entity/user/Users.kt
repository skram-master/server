package com.skram_master.infrastructure.datasource.entity.user

import com.skram_master.infrastructure.datasource.entity.TimestampUUIDTable

internal object Users : TimestampUUIDTable("Users") {
    val name = varchar("name", 255)
}
