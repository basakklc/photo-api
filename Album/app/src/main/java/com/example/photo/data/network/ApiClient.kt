package com.example.photo.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {

        var retrofit: Retrofit? = null
        var BaseURL:String="https://jsonplaceholder.typicode.com/"

        fun getClient(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BaseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(OkHttpClient())
                    .build()
                return retrofit as Retrofit
            }
            return retrofit as Retrofit
        }
    }


}