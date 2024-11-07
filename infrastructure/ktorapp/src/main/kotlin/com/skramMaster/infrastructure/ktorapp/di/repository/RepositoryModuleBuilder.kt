package com.skramMaster.infrastructure.ktorapp.di.repository

import com.skramMaster.infrastructure.ktorapp.di.repository.article.ArticleModuleBuilder
import org.koin.core.module.Module

object RepositoryModuleBuilder {
    fun build(): Array<Module> = arrayOf(
        ArticleModuleBuilder.build()
    )
}