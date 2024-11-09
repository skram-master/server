package com.skramMaster.infrastructure.ktorapp.di.repository

import com.skramMaster.domain.repository.ArticleRepository
import com.skramMaster.infrastructure.datasource.repository.ArticleRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

object RepositoryDIModuleBuilder {
    fun build(): Module = module {
        single<ArticleRepository> { ArticleRepositoryImpl() }
    }
}