package com.skramMaster.infrastructure.ktorapp

import com.skramMaster.infrastructure.ktorapp.plugin.configureDatabases
import com.skramMaster.infrastructure.ktorapp.plugin.configureMonitoring
import com.skramMaster.infrastructure.ktorapp.plugin.configureSecurity
import com.skramMaster.infrastructure.ktorapp.plugin.configureSerialization
import com.skramMaster.infrastructure.ktorapp.plugin.configureSockets
import com.skramMaster.infrastructure.ktorapp.plugin.configureSwaggerUI
import io.ktor.server.application.Application

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureSerialization()
    configureSecurity()
//    configureHTTP()
    configureDatabases()
    configureSockets()
    configureMonitoring()
    configureSwaggerUI()
    configureRouting()
}
