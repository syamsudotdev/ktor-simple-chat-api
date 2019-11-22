# ktor-simple-chat-api
Simple chatting ReSTful API with Websocket for notification

## How to run
### Windows
`gradlew run`
### *nix
`./gradlew run`

This project will run on `localhost:8080`

### HTTP Method
#### Storing Message
Send your message to `http://localhost:8080/message` via HTTP POST method
header `Content-Type:application/json`
body `{"message":"your message"}`

`curl -X POST http://0.0.0.0:8080/messages -H 'Content-Type: application/json' -d '{"message":"something"}'`

#### Get stored messages
Get your previously posted message(s) via `http://localhost:8080/message` via HTTP GET method

`curl -X GET http://0.0.0.0:8080/messages`

### Websocket
To get notification on every new stored message, listen to `http://localhost:8080/ws-message` socket
