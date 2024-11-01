package com.skramMaster

import com.skramMaster.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    // TODO: use Embedded server
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureSecurity()
    configureHTTP()
    configureDatabases()
    configureSockets()
    configureMonitoring()
    configureRouting()
}
