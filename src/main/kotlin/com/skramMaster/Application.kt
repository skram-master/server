package com.skramMaster

import com.skramMaster.plugins.configureDatabases
import com.skramMaster.plugins.configureMonitoring
import com.skramMaster.plugins.configureSecurity
import com.skramMaster.plugins.configureSerialization
import com.skramMaster.plugins.configureSockets
import io.ktor.server.application.Application

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureSecurity()
//    configureHTTP()
    configureDatabases()
    configureSockets()
    configureMonitoring()
    configureRouting()
}
