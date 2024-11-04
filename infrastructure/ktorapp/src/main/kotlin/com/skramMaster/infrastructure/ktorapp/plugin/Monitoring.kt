package com.skramMaster.infrastructure.ktorapp.plugin

import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.request.*

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
