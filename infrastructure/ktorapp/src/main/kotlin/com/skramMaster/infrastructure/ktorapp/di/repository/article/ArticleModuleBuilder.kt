package com.skramMaster.infrastructure.ktorapp.di.repository.article

import com.skramMaster.domain.repository.ArticleRepository
import com.skramMaster.infrastructure.datasource.repository.ArticleRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

object ArticleModuleBuilder {
    fun build(): Module = module {
        single<ArticleRepository> { ArticleRepositoryImpl() }
    }
}