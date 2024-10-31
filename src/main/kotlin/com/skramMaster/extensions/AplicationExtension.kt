package com.skramMaster.extensions

import io.ktor.server.application.Application

fun Application.isDevelopment(): Boolean {
    val env = environment.config.propertyOrNull("ktor.environment")?.getString()
    return when (env) {
        "development" -> false
        "production" -> true
        else -> false
    }
}