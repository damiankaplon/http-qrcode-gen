package io.dk

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respondOutputStream
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
	routing {
		post("/generate") {
			val request = call.receive<GenerateQrCode>()
			val qrCodeWriter = QRCodeWriter()
			val bitMatrix = qrCodeWriter.encode(request.text, BarcodeFormat.QR_CODE, 256, 256)
			call.respondOutputStream(ContentType.Image.PNG, HttpStatusCode.OK) {
				MatrixToImageWriter.writeToStream(bitMatrix, "png", this)
			}
		}
	}
}

@Serializable
data class GenerateQrCode(
	val text: String,
)
