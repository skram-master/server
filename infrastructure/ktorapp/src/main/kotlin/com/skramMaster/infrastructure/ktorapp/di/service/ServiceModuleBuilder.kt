package com.skramMaster.infrastructure.ktorapp.di.service

import com.skramMaster.domain.service.ArticleService
import com.skramMaster.domain.service.ArticleServiceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

object ServiceModuleBuilder {
    fun build(): Module = module {
        single<ArticleService> { ArticleServiceImpl(get()) }
    }
}