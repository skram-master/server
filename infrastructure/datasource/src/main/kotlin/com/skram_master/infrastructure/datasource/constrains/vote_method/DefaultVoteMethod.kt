package com.skram_master.infrastructure.datasource.constrains.vote_method

import com.skram_master.enums.VoteMethodType
import com.skram_master.infrastructure.datasource.common.vote_method.VoteMethodOption

private val FibonacciOptions =
    listOf("0", "1", "2", "3", "5", "8", "13", "21", "34", "55", "89", "?", "☕️")
        .mapIndexed { index, value ->
            VoteMethodOption(
                value = value,
                order = index,
            )
        }

private val ModifiedFibonacciOptions =
    listOf("0", "½", "1", "2", "3", "5", "8", "13", "20", "40", "100", "?", "☕️")
        .mapIndexed { index, value ->
            VoteMethodOption(
                value = value,
                order = index,
            )
        }

private val TShirtSizeOptions = listOf("XS", "S", "M", "L", "XL", "?", "☕️")
    .mapIndexed { index, value ->
        VoteMethodOption(
            value = value,
            order = index,
        )
    }

private val PowerOfTwoOptions = listOf("0", "1", "2", "4", "8", "16", "32", "64", "?", "☕️")
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
        override val options: List<VoteMethodOption> = FibonacciOptions,
    ) : DefaultVoteMethod {
        override val type = VoteMethodType.Fibonacci
    }

    data class ModifiedFibonacci(
        override val options: List<VoteMethodOption> = ModifiedFibonacciOptions,
    ) : DefaultVoteMethod {
        override val type = VoteMethodType.ModifiedFibonacci
    }

    data class TShirtSize(
        override val options: List<VoteMethodOption> = TShirtSizeOptions,
    ) : DefaultVoteMethod {
        override val type = VoteMethodType.TShirtSize
    }

    data class PowerOfTwo(
        override val options: List<VoteMethodOption> = PowerOfTwoOptions,
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

        fun all(): List<DefaultVoteMethod> {
            return listOf(
                Fibonacci(),
                ModifiedFibonacci(),
                TShirtSize(),
                PowerOfTwo(),
            )
        }
    }
}
