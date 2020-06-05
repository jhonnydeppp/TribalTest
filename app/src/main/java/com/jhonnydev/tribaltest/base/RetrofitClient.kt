package com.jhonnydev.tribaltest.base

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder


object RetrofitClient {

    private var retrofit: Retrofit? = null

    const val BASE_URL = "https://api.unsplash.com/"
    var gson = GsonBuilder()
        .setLenient()
        .create()

    fun getClient(baseUrl: String = BASE_URL): Retrofit =
        if (retrofit == null) {
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }else
         retrofit!!


    private fun getOkHttpClient(): okhttp3.OkHttpClient {
        val okHttpClient = okhttp3.OkHttpClient().newBuilder()
        okHttpClient.readTimeout(50, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(50, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(50, TimeUnit.SECONDS)
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient.addInterceptor(loggingInterceptor)

        return okHttpClient.build()
    }


}