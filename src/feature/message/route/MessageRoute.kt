package feature.message.route

import feature.message.controller.MessageController
import feature.message.controller.SocketSessionController
import feature.message.request.MessageBody
import feature.message.response.BaseResponse
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.websocket.webSocket
import kotlinx.coroutines.channels.ClosedReceiveChannelException

fun Route.startMessageRouting() {
    httpStoreMessage()
    httpGetMessages()
    socketMessageNotification()
}

fun Route.httpStoreMessage() {
    post("/messages") {
        val body = call.receive<MessageBody>()
        if (MessageController.storeMessage(body)) {
            SocketSessionController.broadcast(body.message ?: "")
            call.respond(HttpStatusCode.OK)
            return@post
        }
        call.respond(HttpStatusCode.BadRequest)
    }
}

fun Route.httpGetMessages() {
    get("/messages") {
        call.respond(HttpStatusCode.OK, BaseResponse(MessageController.getMessages()))
    }
}

fun Route.socketMessageNotification() {
    webSocket("/ws-message") {
        SocketSessionController.registerSession(this)
        try {
            incoming.receive()
        } catch (e: ClosedReceiveChannelException) {
        } finally {
            SocketSessionController.removeSession(this)
        }
    }
}