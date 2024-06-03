package com.svalero.wheatherapp.coomons.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = requestProcedure(chain.request())
        val response = chain.proceed(request)
        return response
    }

    private fun requestProcedure(request: Request): Request {
        val url = request
            .url
            .newBuilder()
            .addQueryParameter(
            "key", "de5553176da64306b86153651221606")
            .addQueryParameter("lang","es")
            .build()
        return request.newBuilder().url(url).build()
    }
}