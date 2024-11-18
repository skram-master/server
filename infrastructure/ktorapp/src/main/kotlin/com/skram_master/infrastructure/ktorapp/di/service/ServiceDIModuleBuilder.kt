package com.skram_master.infrastructure.ktorapp.di.service

import com.skram_master.domain.service.ArticleService
import com.skram_master.domain.service.ArticleServiceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

object ServiceDIModuleBuilder {
    fun build(): Module = module {
        single<ArticleService> { ArticleServiceImpl(get(), get()) }
    }
}
