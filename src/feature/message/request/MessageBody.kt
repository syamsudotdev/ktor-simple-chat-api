package feature.message.request

data class MessageBody(val message: String?) {
    fun validate() = message?.isNotBlank() == true
}