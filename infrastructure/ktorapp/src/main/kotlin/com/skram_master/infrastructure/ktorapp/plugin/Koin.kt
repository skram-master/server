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
    val name = environment.config.property("postgres.name").getString()
    val user = environment.config.property("postgres.user").getString()
    val password = environment.config.property("postgres.password").getString()

    install(Koin) {
        slf4jLogger()

        modules(
            DatabaseDIModuleBuilder.build(name = name, user = user, password = password),
            RepositoryDIModuleBuilder.build(),
            ServiceDIModuleBuilder.build(),
            ControllerDIModuleBuilder.build(),
        )
    }
}
