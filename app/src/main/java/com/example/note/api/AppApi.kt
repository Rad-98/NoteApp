package com.example.note.api

import com.example.note.api.models.news.NewsResult
import com.example.note.api.models.quotes.QuoteResult
import retrofit2.Response
import retrofit2.http.GET

interface AppApi {

    @GET("/khabar/?kind=iran")
    suspend fun getNews(): Response<NewsResult>

    @GET("/quotes")
    suspend fun getQuotes() : Response<QuoteResult>




}