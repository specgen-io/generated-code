package test_service.utils

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

class UrlBuilder(baseUrl: String) {
    private val urlBuilder: HttpUrl.Builder

    init {
        this.urlBuilder = baseUrl.toHttpUrl().newBuilder()
    }

    fun addQueryParameter(name: String, value: Any): UrlBuilder {
        val valueStr = value.toString()
        urlBuilder.addQueryParameter(name, valueStr)
        return this
    }

    fun <T> addQueryParameter(name: String, values: List<T>): UrlBuilder {
        for (value in values) {
            this.addQueryParameter(name, value!!)
        }
        return this
    }

    fun addPathSegments(value: String): UrlBuilder {
        this.urlBuilder.addPathSegments(value)
        return this
    }

    fun addPathParameter(value: Any): UrlBuilder {
        val valueStr = value.toString()
        this.urlBuilder.addPathSegment(valueStr)
        return this
    }

    fun build(): HttpUrl {
        return this.urlBuilder.build()
    }
}