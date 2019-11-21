package net.mnsam

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.websocket.WebSockets
import java.time.Duration


fun Application.installPlugin() {
    install(CORS) { anyHost() }

    install(DefaultHeaders)

    install(ContentNegotiation) { gson() }

    install(CallLogging)

    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
    }
}