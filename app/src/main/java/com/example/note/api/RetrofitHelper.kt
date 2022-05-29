package com.example.note.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitHelper {

    val baseUrlQoute = "https://quotable.io/"
    val baseUrlNews = "https://api.codebazan.ir/khabar/?kind=iran"

    fun getInstanceQoute(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrlQoute)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstanceNews(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrlNews)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}