package feature.message.controller

import feature.message.request.MessageBody

object MessageController {
    private val messages = mutableListOf<String>()

    fun storeMessage(message: MessageBody): Boolean {
        if (message.validate()) {
            messages.add(message.message ?: "")
            return true
        }
        return false
    }

    fun getMessages() = messages.toList()

}