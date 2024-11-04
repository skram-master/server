package com.skramMaster.infrastructure.ktorapp

import com.skramMaster.infrastructure.ktorapp.plugin.*
import io.ktor.server.application.Application

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureSecurity()
//    configureHTTP()
    configureSockets()
    configureSerialization()
    configureMonitoring()
    configureDatabase()
    configureSwaggerUI()
    configureRouting()
}
