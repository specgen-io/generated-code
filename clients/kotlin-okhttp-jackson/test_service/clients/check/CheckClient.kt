package test_service.clients.check

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.annotation.JsonSubTypes.*
import com.fasterxml.jackson.core.type.*
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.math.BigDecimal
import java.time.*
import java.util.*
import java.io.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.*
import test_service.*
import test_service.utils.*
import test_service.models.*
import test_service.json.setupObjectMapper

class CheckClient(private val baseUrl: String) {
	private var objectMapper: ObjectMapper
	private var client: OkHttpClient

	init {
		objectMapper = setupObjectMapper(jacksonObjectMapper())
		client = OkHttpClient()
	}

	private val logger: Logger = LoggerFactory.getLogger(CheckClient::class.java)

	fun checkEmpty() {
		val url = UrlBuilder(baseUrl)
		url.addPathSegments("check/empty")

		val request = RequestBuilder("GET", url.build(), null)

		logger.info("Sending request, operationId: check.check_empty, method: GET, url: /check/empty")
		val response = try {
			client.newCall(request.build()).execute()
		} catch (e: IOException) {
			val errorMessage = "Failed to execute the request " + e.message
			logger.error(errorMessage)
			throw ClientException(errorMessage, e)
		}

		return when (response.code) {
			200 -> {
				logger.info("Received response with status code {}", response.code)
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}

	fun checkEmptyResponse(body: Message) {
		val bodyJson = try {
			objectMapper.writeValueAsString(body)
		} catch (e: JsonProcessingException) {
			val errorMessage = "Failed to serialize JSON " + e.message
			logger.error(errorMessage)
			throw ClientException(errorMessage, e)
		}

		val requestBody = bodyJson.toRequestBody("application/json".toMediaTypeOrNull())
		val url = UrlBuilder(baseUrl)
		url.addPathSegments("check/empty_response")

		val request = RequestBuilder("POST", url.build(), requestBody)

		logger.info("Sending request, operationId: check.check_empty_response, method: POST, url: /check/empty_response")
		val response = try {
			client.newCall(request.build()).execute()
		} catch (e: IOException) {
			val errorMessage = "Failed to execute the request " + e.message
			logger.error(errorMessage)
			throw ClientException(errorMessage, e)
		}

		return when (response.code) {
			200 -> {
				logger.info("Received response with status code {}", response.code)
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}

	fun checkForbidden(): CheckForbiddenResponse {
		val url = UrlBuilder(baseUrl)
		url.addPathSegments("check/forbidden")

		val request = RequestBuilder("GET", url.build(), null)

		logger.info("Sending request, operationId: check.check_forbidden, method: GET, url: /check/forbidden")
		val response = try {
			client.newCall(request.build()).execute()
		} catch (e: IOException) {
			val errorMessage = "Failed to execute the request " + e.message
			logger.error(errorMessage)
			throw ClientException(errorMessage, e)
		}

		return when (response.code) {
			200 -> {
				logger.info("Received response with status code {}", response.code)
				val responseBody = try {
					objectMapper.readValue(response.body!!.string(), object: TypeReference<Message>(){})
				} catch (e: IOException) {
					val errorMessage = "Failed to deserialize response body " + e.message
					logger.error(errorMessage)
					throw ClientException(errorMessage, e)
				}
				CheckForbiddenResponse.Ok(responseBody)
			}
			403 -> {
				logger.info("Received response with status code {}", response.code)
				CheckForbiddenResponse.Forbidden()
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}

	fun sameOperationName(): SameOperationNameResponse {
		val url = UrlBuilder(baseUrl)
		url.addPathSegments("check/same_operation_name")

		val request = RequestBuilder("GET", url.build(), null)

		logger.info("Sending request, operationId: check.same_operation_name, method: GET, url: /check/same_operation_name")
		val response = try {
			client.newCall(request.build()).execute()
		} catch (e: IOException) {
			val errorMessage = "Failed to execute the request " + e.message
			logger.error(errorMessage)
			throw ClientException(errorMessage, e)
		}

		return when (response.code) {
			200 -> {
				logger.info("Received response with status code {}", response.code)
				SameOperationNameResponse.Ok()
			}
			403 -> {
				logger.info("Received response with status code {}", response.code)
				SameOperationNameResponse.Forbidden()
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}
}
