package com.skramMaster.infrastructure.ktorapp.di.controller

import com.skramMaster.controller.article.ArticleController
import org.koin.dsl.module

object ControllerDIModuleBuilder {
    fun build() = module {
        factory { ArticleController(get()) }
    }
}