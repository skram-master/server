package com.skram_master.infrastructure.ktorapp.di.repository

import com.skram_master.domain.repository.ArticleRepository
import com.skram_master.infrastructure.datasource.repository.ArticleRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

object RepositoryDIModuleBuilder {
    fun build(): Module = module {
        single<ArticleRepository> { ArticleRepositoryImpl() }
    }
}
