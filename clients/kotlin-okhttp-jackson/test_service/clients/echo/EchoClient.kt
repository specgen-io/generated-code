package test_service.clients.echo

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.annotation.JsonSubTypes.*
import com.fasterxml.jackson.core.type.*
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

class EchoClient(
	private val baseUrl: String,
	private val client: OkHttpClient = OkHttpClient(),
	private val objectMapper: ObjectMapper = setupObjectMapper(jacksonObjectMapper())
) {
	private val logger: Logger = LoggerFactory.getLogger(EchoClient::class.java)

	fun echoBodyString(body: String): String {
		val requestBody = body.toRequestBody("text/plain".toMediaTypeOrNull())
		val url = UrlBuilder(baseUrl)
		url.addPathSegment("echo/body_string")

		val request = RequestBuilder("POST", url.build(), requestBody)

		logger.info("Sending request, operationId: echo.echo_body_string, method: POST, url: /echo/body_string")
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
					response.body!!.string()
				} catch (e: IOException) {
					val errorMessage = "Failed to convert response body to string " + e.message
					logger.error(errorMessage)
					throw ClientException(errorMessage, e)
				}
				responseBody
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}

	fun echoBodyModel(body: Message): Message {
		val bodyJson = try {
			objectMapper.writeValueAsString(body)
		} catch (e: JsonProcessingException) {
			val errorMessage = "Failed to serialize JSON " + e.message
			logger.error(errorMessage)
			throw ClientException(errorMessage, e)
		}

		val requestBody = bodyJson.toRequestBody("application/json".toMediaTypeOrNull())
		val url = UrlBuilder(baseUrl)
		url.addPathSegment("echo/body_model")

		val request = RequestBuilder("POST", url.build(), requestBody)

		logger.info("Sending request, operationId: echo.echo_body_model, method: POST, url: /echo/body_model")
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
				responseBody
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}

	fun echoBodyArray(body: Array<String>): Array<String> {
		val bodyJson = try {
			objectMapper.writeValueAsString(body)
		} catch (e: JsonProcessingException) {
			val errorMessage = "Failed to serialize JSON " + e.message
			logger.error(errorMessage)
			throw ClientException(errorMessage, e)
		}

		val requestBody = bodyJson.toRequestBody("application/json".toMediaTypeOrNull())
		val url = UrlBuilder(baseUrl)
		url.addPathSegment("echo/body_array")

		val request = RequestBuilder("POST", url.build(), requestBody)

		logger.info("Sending request, operationId: echo.echo_body_array, method: POST, url: /echo/body_array")
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
					objectMapper.readValue(response.body!!.string(), object: TypeReference<Array<String>>(){})
				} catch (e: IOException) {
					val errorMessage = "Failed to deserialize response body " + e.message
					logger.error(errorMessage)
					throw ClientException(errorMessage, e)
				}
				responseBody
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}

	fun echoBodyMap(body: Map<String, String>): Map<String, String> {
		val bodyJson = try {
			objectMapper.writeValueAsString(body)
		} catch (e: JsonProcessingException) {
			val errorMessage = "Failed to serialize JSON " + e.message
			logger.error(errorMessage)
			throw ClientException(errorMessage, e)
		}

		val requestBody = bodyJson.toRequestBody("application/json".toMediaTypeOrNull())
		val url = UrlBuilder(baseUrl)
		url.addPathSegment("echo/body_map")

		val request = RequestBuilder("POST", url.build(), requestBody)

		logger.info("Sending request, operationId: echo.echo_body_map, method: POST, url: /echo/body_map")
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
					objectMapper.readValue(response.body!!.string(), object: TypeReference<Map<String, String>>(){})
				} catch (e: IOException) {
					val errorMessage = "Failed to deserialize response body " + e.message
					logger.error(errorMessage)
					throw ClientException(errorMessage, e)
				}
				responseBody
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}

	fun echoQuery(intQuery: Int, longQuery: Long, floatQuery: Float, doubleQuery: Double, decimalQuery: BigDecimal, boolQuery: Boolean, stringQuery: String, stringOptQuery: String?, stringDefaultedQuery: String, stringArrayQuery: Array<String>, uuidQuery: UUID, dateQuery: LocalDate, dateArrayQuery: Array<LocalDate>, datetimeQuery: LocalDateTime, enumQuery: Choice): Parameters {
		val url = UrlBuilder(baseUrl)
		url.addPathSegment("echo/query")
		url.addQueryParameter("int_query", intQuery)
		url.addQueryParameter("long_query", longQuery)
		url.addQueryParameter("float_query", floatQuery)
		url.addQueryParameter("double_query", doubleQuery)
		url.addQueryParameter("decimal_query", decimalQuery)
		url.addQueryParameter("bool_query", boolQuery)
		url.addQueryParameter("string_query", stringQuery)
		url.addQueryParameter("string_opt_query", stringOptQuery!!)
		url.addQueryParameter("string_defaulted_query", stringDefaultedQuery)
		url.addQueryParameter("string_array_query", stringArrayQuery)
		url.addQueryParameter("uuid_query", uuidQuery)
		url.addQueryParameter("date_query", dateQuery)
		url.addQueryParameter("date_array_query", dateArrayQuery)
		url.addQueryParameter("datetime_query", datetimeQuery)
		url.addQueryParameter("enum_query", enumQuery)

		val request = RequestBuilder("GET", url.build(), null)

		logger.info("Sending request, operationId: echo.echo_query, method: GET, url: /echo/query")
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
					objectMapper.readValue(response.body!!.string(), object: TypeReference<Parameters>(){})
				} catch (e: IOException) {
					val errorMessage = "Failed to deserialize response body " + e.message
					logger.error(errorMessage)
					throw ClientException(errorMessage, e)
				}
				responseBody
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}

	fun echoHeader(intHeader: Int, longHeader: Long, floatHeader: Float, doubleHeader: Double, decimalHeader: BigDecimal, boolHeader: Boolean, stringHeader: String, stringOptHeader: String?, stringDefaultedHeader: String, stringArrayHeader: Array<String>, uuidHeader: UUID, dateHeader: LocalDate, dateArrayHeader: Array<LocalDate>, datetimeHeader: LocalDateTime, enumHeader: Choice): Parameters {
		val url = UrlBuilder(baseUrl)
		url.addPathSegment("echo/header")

		val request = RequestBuilder("GET", url.build(), null)
		request.addHeaderParameter("Int-Header", intHeader)
		request.addHeaderParameter("Long-Header", longHeader)
		request.addHeaderParameter("Float-Header", floatHeader)
		request.addHeaderParameter("Double-Header", doubleHeader)
		request.addHeaderParameter("Decimal-Header", decimalHeader)
		request.addHeaderParameter("Bool-Header", boolHeader)
		request.addHeaderParameter("String-Header", stringHeader)
		request.addHeaderParameter("String-Opt-Header", stringOptHeader!!)
		request.addHeaderParameter("String-Defaulted-Header", stringDefaultedHeader)
		request.addHeaderParameter("String-Array-Header", stringArrayHeader)
		request.addHeaderParameter("Uuid-Header", uuidHeader)
		request.addHeaderParameter("Date-Header", dateHeader)
		request.addHeaderParameter("Date-Array-Header", dateArrayHeader)
		request.addHeaderParameter("Datetime-Header", datetimeHeader)
		request.addHeaderParameter("Enum-Header", enumHeader)

		logger.info("Sending request, operationId: echo.echo_header, method: GET, url: /echo/header")
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
					objectMapper.readValue(response.body!!.string(), object: TypeReference<Parameters>(){})
				} catch (e: IOException) {
					val errorMessage = "Failed to deserialize response body " + e.message
					logger.error(errorMessage)
					throw ClientException(errorMessage, e)
				}
				responseBody
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}

	fun echoUrlParams(intUrl: Int, longUrl: Long, floatUrl: Float, doubleUrl: Double, decimalUrl: BigDecimal, boolUrl: Boolean, stringUrl: String, uuidUrl: UUID, dateUrl: LocalDate, datetimeUrl: LocalDateTime, enumUrl: Choice): UrlParameters {
		val url = UrlBuilder(baseUrl)
		url.addPathSegment("echo/url_params")
		url.addPathSegment(intUrl)
		url.addPathSegment(longUrl)
		url.addPathSegment(floatUrl)
		url.addPathSegment(doubleUrl)
		url.addPathSegment(decimalUrl)
		url.addPathSegment(boolUrl)
		url.addPathSegment(stringUrl)
		url.addPathSegment(uuidUrl)
		url.addPathSegment(dateUrl)
		url.addPathSegment(datetimeUrl)
		url.addPathSegment(enumUrl)

		val request = RequestBuilder("GET", url.build(), null)

		logger.info("Sending request, operationId: echo.echo_url_params, method: GET, url: /echo/url_params/{int_url}/{long_url}/{float_url}/{double_url}/{decimal_url}/{bool_url}/{string_url}/{uuid_url}/{date_url}/{datetime_url}/{enum_url}")
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
					objectMapper.readValue(response.body!!.string(), object: TypeReference<UrlParameters>(){})
				} catch (e: IOException) {
					val errorMessage = "Failed to deserialize response body " + e.message
					logger.error(errorMessage)
					throw ClientException(errorMessage, e)
				}
				responseBody
			}
			else -> {
				val errorMessage = "Unexpected status code received: " + response.code
				logger.error(errorMessage)
				throw ClientException(errorMessage)
			}
		}
	}

	fun echoEverything(body: Message, floatQuery: Float, boolQuery: Boolean, uuidHeader: UUID, datetimeHeader: LocalDateTime, dateUrl: LocalDate, decimalUrl: BigDecimal): EchoEverythingResponse {
		val bodyJson = try {
			objectMapper.writeValueAsString(body)
		} catch (e: JsonProcessingException) {
			val errorMessage = "Failed to serialize JSON " + e.message
			logger.error(errorMessage)
			throw ClientException(errorMessage, e)
		}

		val requestBody = bodyJson.toRequestBody("application/json".toMediaTypeOrNull())
		val url = UrlBuilder(baseUrl)
		url.addPathSegment("echo/everything")
		url.addPathSegment(dateUrl)
		url.addPathSegment(decimalUrl)
		url.addQueryParameter("float_query", floatQuery)
		url.addQueryParameter("bool_query", boolQuery)

		val request = RequestBuilder("POST", url.build(), requestBody)
		request.addHeaderParameter("Uuid-Header", uuidHeader)
		request.addHeaderParameter("Datetime-Header", datetimeHeader)

		logger.info("Sending request, operationId: echo.echo_everything, method: POST, url: /echo/everything/{date_url}/{decimal_url}")
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
					objectMapper.readValue(response.body!!.string(), object: TypeReference<Everything>(){})
				} catch (e: IOException) {
					val errorMessage = "Failed to deserialize response body " + e.message
					logger.error(errorMessage)
					throw ClientException(errorMessage, e)
				}
				EchoEverythingResponse.Ok(responseBody)
			}
			403 -> {
				logger.info("Received response with status code {}", response.code)
				EchoEverythingResponse.Forbidden()
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
		url.addPathSegment("echo/same_operation_name")

		val request = RequestBuilder("GET", url.build(), null)

		logger.info("Sending request, operationId: echo.same_operation_name, method: GET, url: /echo/same_operation_name")
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
