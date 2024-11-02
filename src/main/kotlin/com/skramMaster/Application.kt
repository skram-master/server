package com.skramMaster

import com.skramMaster.plugin.configureDatabases
import com.skramMaster.plugin.configureMonitoring
import com.skramMaster.plugin.configureSecurity
import com.skramMaster.plugin.configureSerialization
import com.skramMaster.plugin.configureSockets
import com.skramMaster.plugin.configureSwaggerUI
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
