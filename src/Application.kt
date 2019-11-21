package net.mnsam

import feature.message.route.startMessageRouting
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, module = Application::mainModule).start(wait = true)
}

fun Application.mainModule() {
    installPlugin()
    route()
}

fun Application.route() {
    routing {
        get("/") {
            call.respondText("Welcome to Ktor!", contentType = ContentType.Text.Plain)
        }

        startMessageRouting()
    }
}