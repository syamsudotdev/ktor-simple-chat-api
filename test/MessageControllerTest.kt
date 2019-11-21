package net.mnsam

import feature.message.controller.MessageController
import feature.message.request.MessageBody
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MessageControllerTest {
    @Test
    fun storeMessageSuccess() {
        val messageBody = MessageBody("something")
        assertTrue { MessageController.storeMessage(messageBody) }
    }

    @Test
    fun storeMessageWhitespaceError() {
        val messageBody = MessageBody(" ")
        assertFalse { MessageController.storeMessage(messageBody) }
    }
}