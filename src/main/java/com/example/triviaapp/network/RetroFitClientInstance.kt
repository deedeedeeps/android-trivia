package com.example.triviaapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClientInstance {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://opentdb.com/"

    val retrofitInstance: Retrofit?
        get(){
            if (retrofit == null){
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}