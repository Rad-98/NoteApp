package com.example.note.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.note.api.models.news.NewsResult
import com.example.note.repositories.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel constructor(application: Application):AndroidViewModel(application) {

    private val repository = NewsRepository()
    val addResult :MutableLiveData<Response<NewsResult>> = MutableLiveData()

    fun getNews(){
        viewModelScope.launch (Dispatchers.IO){
            repository.getNews().let {
                addResult.postValue(it)
            }
        }
    }



}