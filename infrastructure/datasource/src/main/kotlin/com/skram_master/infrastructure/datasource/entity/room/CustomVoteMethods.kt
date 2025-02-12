package com.skram_master.infrastructure.datasource.entity.room

import com.skram_master.enums.VoteMethodType
import com.skram_master.infrastructure.datasource.entity.TimestampIntIdTable
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.json.json
import org.jetbrains.exposed.sql.selectAll
import java.util.logging.Logger

@Serializable
internal data class CustomVoteMethodOption(
    val value: String,
    val order: Int,
)

internal object CustomVoteMethods : TimestampIntIdTable("CustomVoteMethods") {
    val type = enumeration("type", VoteMethodType::class).index()
    val options = json<Array<CustomVoteMethodOption>>("options", Json.Default)

    fun seed() {
        if (CustomVoteMethods.selectAll().count() > 0) {
            return
        }
        val logger = Logger.getGlobal()
        logger.info("VoteMethod seed data inserting...")

        // Fibonacci
        val fibonacciOptions = listOf(
            "0", "1", "2", "3", "5", "8", "13", "21", "34", "55", "89", "?", "☕️",
        )
        CustomVoteMethods.insert {
            it[type] = VoteMethodType.Fibonacci
            it[options] = generateVoteMethodOptions(fibonacciOptions)
        }

        // ModifiedFibonacci
        val modifiedFibonacciOptions = listOf(
            "0", "½", "1", "2", "3", "5", "8", "13", "20", "40", "100", "?", "☕️",
        )
        CustomVoteMethods.insert {
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
        CustomVoteMethods.insert {
            it[type] = VoteMethodType.TShirtSize
            it[options] = generateVoteMethodOptions(tShirtSizeOptions)
        }

        // PowerOfTwo
        val powerOfTwoOptions = listOf(
            "0", "1", "2", "4", "8", "16", "32", "64", "?", "☕️",
        )
        CustomVoteMethods.insert {
            it[type] = VoteMethodType.PowerOfTwo
            it[options] = generateVoteMethodOptions(powerOfTwoOptions)
        }

        logger.info("VoteMethod seed data inserted.")
    }

    private fun generateVoteMethodOptions(options: List<String>): Array<CustomVoteMethodOption> {
        return options.mapIndexed { index, value ->
            CustomVoteMethodOption(value = value, order = index)
        }.toTypedArray()
    }
}
