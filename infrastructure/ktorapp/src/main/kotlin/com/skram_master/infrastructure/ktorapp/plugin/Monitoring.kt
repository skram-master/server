package com.skram_master.infrastructure.ktorapp.plugin

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.request.httpMethod
import io.ktor.server.request.path

fun Application.configureMonitoring() {
    install(CallLogging) {
        filter { call -> call.request.path().startsWith("/") }
        format { call ->
            val status = call.response.status()
            val path = call.request.path()
            val httpMethod = call.request.httpMethod.value
            val userAgent = call.request.headers["User-Agent"]
            "$httpMethod $path: $status , User agent: $userAgent"
        }
    }
}
