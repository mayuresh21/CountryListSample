package com.mayuresh.countries.data.service

import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.io.IOException

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        // Log the request
        val requestBody = request.body
        val requestContent = requestBody?.let { bodyToString(it) } ?: "No Request Body"
        val requestLog = "Request: ${request.method} ${request.url}\n$requestContent"

        // Log the response
        val responseBody = response.body
        val responseContent = responseBody?.let { bodyToString(it) } ?: "No Response Body"
        val responseLog = "Response: ${response.code} $responseContent"

        // Print the logs or save them to a file as needed
        println(requestLog)
        println(responseLog)

        return response
    }

    private fun bodyToString(requestBody: okhttp3.RequestBody): String {
        try {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "Error reading request body."
        }
    }
    private fun bodyToString(responseBody: okhttp3.ResponseBody): String {
        try {
            val buffer = Buffer()

            return responseBody.byteStream().toString()
        } catch (e: IOException) {
            return "Error reading request body."
        }
    }
}