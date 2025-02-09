package com.skram_master.infrastructure.datasource.entity.room

import com.skram_master.infrastructure.datasource.entity.TimestampIntIdTable
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.json.json
import org.jetbrains.exposed.sql.selectAll
import java.util.logging.Logger

internal enum class VoteMethodType {
    Fibonacci,
    ModifiedFibonacci,
    TShirtSize,
    PowerOfTwo,
    Custom,
}

@Serializable
internal data class VoteMethodOption(
    val value: String,
    val order: Int,
)

internal object VoteMethod : TimestampIntIdTable("VoteMethod") {
    val type = enumeration("type", VoteMethodType::class).uniqueIndex()
    val options = json<Array<VoteMethodOption>>("options", Json.Default)

    fun seed() {
        if (VoteMethod.selectAll().count() > 0) {
            return
        }
        val logger = Logger.getGlobal()
        logger.info("VoteMethod seed data inserting...")

        // Fibonacci
        val fibonacciOptions = listOf(
            "0", "1", "2", "3", "5", "8", "13", "21", "34", "55", "89", "?", "☕️",
        )
        VoteMethod.insert {
            it[type] = VoteMethodType.Fibonacci
            it[options] = generateVoteMethodOptions(fibonacciOptions)
        }

        // ModifiedFibonacci
        val modifiedFibonacciOptions = listOf(
            "0", "½", "1", "2", "3", "5", "8", "13", "20", "40", "100", "?", "☕️",
        )
        VoteMethod.insert {
            it[type] = VoteMethodType.ModifiedFibonacci
            it[options] = generateVoteMethodOptions(modifiedFibonacciOptions)
        }

        // TShirtSize
        val tShirtSizeOptions = listOf(
            "XS",
            "S",
            "M",
            "L",
            "XL",
            "?",
            "☕️",
        )
        VoteMethod.insert {
            it[type] = VoteMethodType.TShirtSize
            it[options] = generateVoteMethodOptions(tShirtSizeOptions)
        }

        // PowerOfTwo
        val powerOfTwoOptions = listOf(
            "0", "1", "2", "4", "8", "16", "32", "64", "?", "☕️",
        )
        VoteMethod.insert {
            it[type] = VoteMethodType.PowerOfTwo
            it[options] = generateVoteMethodOptions(powerOfTwoOptions)
        }

        logger.info("VoteMethod seed data inserted.")
    }

    private fun generateVoteMethodOptions(options: List<String>): Array<VoteMethodOption> {
        return options.mapIndexed { index, value ->
            VoteMethodOption(value = value, order = index)
        }.toTypedArray()
    }
}
