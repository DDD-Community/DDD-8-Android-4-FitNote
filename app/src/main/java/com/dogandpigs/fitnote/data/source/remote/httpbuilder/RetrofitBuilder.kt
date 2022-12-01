package com.dogandpigs.fitnote.data.source.remote.httpbuilder

import com.dogandpigs.fitnote.core.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {
    private val baseUrl = "http://52.79.105.64/"
    
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).apply {
            client(OkHttpClient.Builder().apply {
                readTimeout(60, TimeUnit.SECONDS) //1분
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }.addNetworkInterceptor(NetworkInterceptor()).build())
            baseUrl(baseUrl)
        }.build()
    }
    
    inner class NetworkInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val orgRequest = chain.request()
            
            val builder = orgRequest.newBuilder().apply {
                TokenManager.accessToken?.let { token ->
                    addHeader("Authorization", token)
                }
                
            }
            return chain.proceed(builder.build())
        }
    }
}