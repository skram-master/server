package com.skramMaster

import com.skramMaster.plugins.configureDatabases
import com.skramMaster.plugins.configureMonitoring
import com.skramMaster.plugins.configureSecurity
import com.skramMaster.plugins.configureSerialization
import com.skramMaster.plugins.configureSockets
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, watchPaths = listOf("classes")) {
        configureSerialization()
        configureSecurity()
//    configureHTTP()
        configureDatabases()
        configureSockets()
        configureMonitoring()
        configureRouting()
    }.start(wait = true)
}

