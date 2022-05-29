package com.example.note.repositories

import com.example.note.api.models.news.NewsEntity
import com.example.note.api.models.news.NewsResult
import retrofit2.Response

class NewsRepository :BaseRepository() {

    suspend fun getNews():Response<NewsResult>{
        return newsApi.getNews()
    }
}