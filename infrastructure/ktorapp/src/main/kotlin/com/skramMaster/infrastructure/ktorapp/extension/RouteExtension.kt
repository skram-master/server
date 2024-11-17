package com.skramMaster.infrastructure.ktorapp.extension

import io.github.smiley4.ktorswaggerui.dsl.routes.OpenApiRoute
import io.github.smiley4.ktorswaggerui.dsl.routing.documentation
import io.ktor.http.HttpMethod
import io.ktor.server.resources.handle
import io.ktor.server.resources.resource
import io.ktor.server.routing.Route
import io.ktor.server.routing.RoutingContext
import io.ktor.server.routing.method

inline fun <reified T : Any> Route.get(
    noinline builder: OpenApiRoute.() -> Unit = { },
    noinline body: suspend RoutingContext.(T) -> Unit,
): Route {
    return documentation(builder) {
        resource<T> {
            method(HttpMethod.Get) {
                handle(body)
            }
        }
    }
}

inline fun <reified T : Any> Route.post(
    noinline builder: OpenApiRoute.() -> Unit = { },
    noinline body: suspend RoutingContext.(T) -> Unit,
): Route {
    return documentation(builder) {
        resource<T> {
            method(HttpMethod.Post) {
                handle(body)
            }
        }
    }
}

inline fun <reified T : Any> Route.put(
    noinline builder: OpenApiRoute.() -> Unit = { },
    noinline body: suspend RoutingContext.(T) -> Unit,
): Route {
    return documentation(builder) {
        resource<T> {
            method(HttpMethod.Put) {
                handle(body)
            }
        }
    }
}

inline fun <reified T : Any> Route.delete(
    noinline builder: OpenApiRoute.() -> Unit = { },
    noinline body: suspend RoutingContext.(T) -> Unit,
): Route {
    return documentation(builder) {
        resource<T> {
            method(HttpMethod.Delete) {
                handle(body)
            }
        }
    }
}

inline fun <reified T : Any> Route.patch(
    noinline builder: OpenApiRoute.() -> Unit = { },
    noinline body: suspend RoutingContext.(T) -> Unit,
): Route {
    return documentation(builder) {
        resource<T> {
            method(HttpMethod.Patch) {
                handle(body)
            }
        }
    }
}

inline fun <reified T : Any> Route.options(
    noinline builder: OpenApiRoute.() -> Unit = { },
    noinline body: suspend RoutingContext.(T) -> Unit,
): Route {
    return documentation(builder) {
        resource<T> {
            method(HttpMethod.Options) {
                handle(body)
            }
        }
    }
}

inline fun <reified T : Any> Route.head(
    noinline builder: OpenApiRoute.() -> Unit = { },
    noinline body: suspend RoutingContext.(T) -> Unit,
): Route {
    return documentation(builder) {
        resource<T> {
            method(HttpMethod.Head) {
                handle(body)
            }
        }
    }
}
