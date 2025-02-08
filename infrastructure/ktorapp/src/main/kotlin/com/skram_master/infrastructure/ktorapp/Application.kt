package com.skram_master.infrastructure.ktorapp

import com.skram_master.infrastructure.ktorapp.plugin.configureDatabase
import com.skram_master.infrastructure.ktorapp.plugin.configureHTTP
import com.skram_master.infrastructure.ktorapp.plugin.configureKoin
import com.skram_master.infrastructure.ktorapp.plugin.configureMonitoring
import com.skram_master.infrastructure.ktorapp.plugin.configureSecurity
import com.skram_master.infrastructure.ktorapp.plugin.configureSerialization
import com.skram_master.infrastructure.ktorapp.plugin.configureSockets
import com.skram_master.infrastructure.ktorapp.plugin.configureSwaggerUI
import com.skram_master.infrastructure.ktorapp.plugin.configureValidation
import io.ktor.server.application.Application

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureSecurity()
    configureHTTP()
    configureSockets()
    configureValidation()
    configureSerialization()
    configureMonitoring()
    configureDatabase()
    configureSwaggerUI()
    configureRouting()
}
