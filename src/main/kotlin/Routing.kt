package io.dk

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.serialization.Serializable
import qrcode.QRCode

fun Application.configureRouting() {
	routing {
		post("/generate") {
			val request = call.receive<GenerateQrCode>()
			val qrCode = QRCode.ofSquares().build(request.text).render()
			call.respond(HttpStatusCode.OK, qrCode.getBytes())
		}
	}
}

@Serializable
data class GenerateQrCode(
	val text: String,
)
