package com.skramMaster.infrastructure.ktorapp.plugin

import com.skramMaster.infrastructure.ktorapp.di.database.DatabaseModuleBuilder
import com.skramMaster.infrastructure.ktorapp.di.repository.RepositoryModuleBuilder
import com.skramMaster.infrastructure.ktorapp.di.service.ServiceModuleBuilder
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    val name = environment.config.property("postgres.name").getString()
    val user = environment.config.property("postgres.user").getString()
    val password = environment.config.property("postgres.password").getString()

    install(Koin) {
        slf4jLogger()

        modules(
            DatabaseModuleBuilder.build(name = name, user = user, password = password),
            RepositoryModuleBuilder.build(),
            ServiceModuleBuilder.build(),
        )
    }
}