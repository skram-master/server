package com.skram_master.infrastructure.datasource.entity.utils

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class CustomTimestampTableTest : ShouldSpec({
    context("convertToSneakCase returns class name in snake case") {
        should("if the class name is Hoge, the function should return hoge") {
            class Hoge : TimestampIntIdTable("Hoge")

            Hoge().tableName shouldBe "hoge"
        }

        should("if the class name is HogeFuga, the function should return hoge_fuga") {
            class HogeFuga : TimestampIntIdTable("HogeFuga")

            HogeFuga().tableName shouldBe "hoge_fuga"
        }

        should("if the class name is HogeFugaFizzBuzz, the function should return hoge_fuga_fizz_buzz") {
            class HogeFugaFizzBuzz : TimestampIntIdTable("HogeFugaFizzBuzz")

            HogeFugaFizzBuzz().tableName shouldBe "hoge_fuga_fizz_buzz"
        }
    }
})
