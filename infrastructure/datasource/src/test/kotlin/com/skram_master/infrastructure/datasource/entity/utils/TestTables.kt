package com.skram_master.infrastructure.datasource.entity.utils

internal object TestIntTable : TimestampIntIdTable("TestIntTable") {
    val name = varchar("name", 255)
}

internal object TestLongTable : TimestampLongIdTable("TestLongTable") {
    val name = varchar("name", 255)
}

internal object TestUUIDTable : TimestampUUIDTable("TestUuidTable") {
    val name = varchar("name", 255)
}
