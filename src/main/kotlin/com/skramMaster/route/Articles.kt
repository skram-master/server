package com.skramMaster.route

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

@Serializable
@Resource("/articles")
data class Articles(val sort: String? = "new")

