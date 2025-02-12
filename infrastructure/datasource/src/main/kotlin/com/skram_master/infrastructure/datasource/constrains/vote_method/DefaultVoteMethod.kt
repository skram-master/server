package com.skram_master.infrastructure.datasource.constrains.vote_method

import com.skram_master.enums.VoteMethodType
import com.skram_master.infrastructure.datasource.common.vote_method.VoteMethodOption

private val fibonacciOptions =
    listOf("0", "1", "2", "3", "5", "8", "13", "21", "34", "55", "89", "?", "☕️")
        .mapIndexed { index, value ->
            VoteMethodOption(
                value = value,
                order = index,
            )
        }

private val modifiedFibonacciOptions =
    listOf("0", "½", "1", "2", "3", "5", "8", "13", "20", "40", "100", "?", "☕️")
        .mapIndexed { index, value ->
            VoteMethodOption(
                value = value,
                order = index,
            )
        }

private val tShirtSizeOptions = listOf("XS", "S", "M", "L", "XL", "?", "☕️")
    .mapIndexed { index, value ->
        VoteMethodOption(
            value = value,
            order = index,
        )
    }

private val powerOfTwoOptions = listOf("0", "1", "2", "4", "8", "16", "32", "64", "?", "☕️")
    .mapIndexed { index, value ->
        VoteMethodOption(
            value = value,
            order = index,
        )
    }

sealed interface DefaultVoteMethod {
    val type: VoteMethodType
    val options: List<VoteMethodOption>

    data class Fibonacci(
        override val options: List<VoteMethodOption> = fibonacciOptions,
    ) : DefaultVoteMethod {
        override val type = VoteMethodType.Fibonacci
    }

    data class ModifiedFibonacci(
        override val options: List<VoteMethodOption> = modifiedFibonacciOptions,
    ) : DefaultVoteMethod {
        override val type = VoteMethodType.ModifiedFibonacci
    }

    data class TShirtSize(
        override val options: List<VoteMethodOption> = tShirtSizeOptions,
    ) : DefaultVoteMethod {
        override val type = VoteMethodType.TShirtSize
    }

    data class PowerOfTwo(
        override val options: List<VoteMethodOption> = powerOfTwoOptions,
    ) : DefaultVoteMethod {
        override val type = VoteMethodType.PowerOfTwo
    }

    companion object {
        fun from(type: VoteMethodType): DefaultVoteMethod {
            return when (type) {
                VoteMethodType.Fibonacci -> Fibonacci()
                VoteMethodType.ModifiedFibonacci -> ModifiedFibonacci()
                VoteMethodType.TShirtSize -> TShirtSize()
                VoteMethodType.PowerOfTwo -> PowerOfTwo()
                else -> throw IllegalArgumentException("Unsupported vote method type: $type")
            }
        }
    }
}
