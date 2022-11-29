package com.thechance.identity.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class IdentityAuthInterceptor @Inject constructor() : Interceptor {

    private val apikey = BuildConfig.API_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl = request.url.newBuilder()
            .addQueryParameter(API_KEY_PARAMETER, apikey)
            .build()

        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }

    companion object {
        private const val API_KEY_PARAMETER = "api_key_token"
    }
}