package com.skram_master.infrastructure.ktorapp.plugin

import com.skram_master.infrastructure.ktorapp.di.controller.ControllerDIModuleBuilder
import com.skram_master.infrastructure.ktorapp.di.database.DatabaseDIModuleBuilder
import com.skram_master.infrastructure.ktorapp.di.repository.RepositoryDIModuleBuilder
import com.skram_master.infrastructure.ktorapp.di.service.ServiceDIModuleBuilder
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    val user = environment.config.property("postgres.user").getString()
    val password = environment.config.property("postgres.password").getString()
    val url = environment.config.property("postgres.url").getString()

    install(Koin) {
        slf4jLogger()

        modules(
            DatabaseDIModuleBuilder.build(user = user, password = password, url = url),
            RepositoryDIModuleBuilder.build(),
            ServiceDIModuleBuilder.build(),
            ControllerDIModuleBuilder.build(),
        )
    }
}
