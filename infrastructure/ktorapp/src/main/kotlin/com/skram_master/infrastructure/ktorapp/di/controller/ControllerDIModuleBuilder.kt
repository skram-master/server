package com.skram_master.infrastructure.ktorapp.di.controller

import com.skram_master.controller.article.ArticleController
import org.koin.dsl.module

object ControllerDIModuleBuilder {
    fun build() = module {
        factory { ArticleController(get()) }
    }
}
