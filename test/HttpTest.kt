package net.mnsam

import com.google.gson.Gson
import feature.message.request.MessageBody
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class HttpTest {
    @Test
    fun testRoot() {
        withTestApplication({ mainModule() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(expected = HttpStatusCode.OK, actual = response.status())
                assertEquals(expected = "Welcome to Ktor!", actual = response.content)
            }
        }
    }

    private val gson = Gson()

    @Test
    fun testSuccessStoreMessage() {
        withTestApplication({ mainModule() }) {
            handleRequest(HttpMethod.Post, "/messages") {
                addHeader("Content-Type", "application/json")
                setBody(gson.toJson(MessageBody("something")))
            }.apply {
                assertEquals(expected = HttpStatusCode.OK, actual = response.status())
            }
        }
    }

    @Test
    fun testWhitespaceStoreMessage() {
        withTestApplication({ mainModule() }) {
            handleRequest(HttpMethod.Post, "/messages") {
                addHeader("Content-Type", "application/json")
                setBody(gson.toJson(MessageBody(" ")))
            }.apply {
                assertEquals(expected = HttpStatusCode.BadRequest, actual = response.status())
            }
        }
    }


}
