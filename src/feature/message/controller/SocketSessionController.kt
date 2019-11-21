package feature.message.controller

import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.send
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object SocketSessionController {
    private val sessionsSet = mutableSetOf<WebSocketSession>()

    fun registerSession(session: WebSocketSession) = sessionsSet.add(session)

    fun removeSession(session: WebSocketSession) = sessionsSet.remove(session)

    fun broadcast(message: String) {
        sessionsSet.forEach { session ->
            CoroutineScope(Dispatchers.IO).launch {
                session.send(message)
            }
        }
    }
}