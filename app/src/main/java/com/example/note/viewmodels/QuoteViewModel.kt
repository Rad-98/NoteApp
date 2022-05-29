package com.example.note.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.note.api.models.news.NewsResult
import com.example.note.api.models.quotes.QuoteResult
import com.example.note.repositories.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class QuoteViewModel constructor(application: Application):AndroidViewModel(application) {

    private val repository:QuoteRepository = QuoteRepository()
    val addResult : MutableLiveData<Response<QuoteResult>> = MutableLiveData()
    val addResult1 : MutableLiveData<List<NewsResult>> = MutableLiveData()

    fun getQuote(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getQuote().let {
                    addResult.postValue(it)
                }
            }catch (e:Exception){

            }

        }
    }

}