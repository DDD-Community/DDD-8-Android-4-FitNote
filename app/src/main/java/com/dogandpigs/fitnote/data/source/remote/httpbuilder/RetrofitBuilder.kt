package com.dogandpigs.fitnote.data.source.remote.httpbuilder

import android.util.Log
import com.dogandpigs.fitnote.core.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {
    private val baseUrl = "http://52.79.105.64"
    
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).apply {
            client(OkHttpClient.Builder().apply {
                readTimeout(60, TimeUnit.SECONDS) //1분
                addInterceptor(NetworkInterceptor())
            }.addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build())
            baseUrl(baseUrl)
        }.build()
    }
    
    inner class NetworkInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val orgRequest = chain.request()
            
            val builder = orgRequest.newBuilder().apply {
                TokenManager.accessToken?.let { token ->
                    addHeader("Authorization", "Bearer $token")
                }
            }
            return try {
                chain.proceed(builder.build())
            } catch (e: Exception) {
                Log.d("test", "intercept: ")
                Response.Builder()
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .code(599)
                    .message(e.message ?: "Network Interceptor exception")
                    .body(e.message?.toResponseBody(null))
                    .build()
            }
        }
    }
}