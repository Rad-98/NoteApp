package com.example.note.repositories

import com.example.note.api.models.quotes.QuoteResult
import retrofit2.Response

class QuoteRepository ():BaseRepository() {

    suspend fun getQuote():Response<QuoteResult>{
        return appApi.getQuotes()
    }

}