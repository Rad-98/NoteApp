package com.example.note.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.note.database.entity.NoteEntity
import com.example.note.repositories.ShowNotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowNotesViewModel constructor(application: Application) :AndroidViewModel(application) {

    private val repository: ShowNotesRepository = ShowNotesRepository()
    val result : MutableLiveData<List<NoteEntity>> = MutableLiveData()
    val resultDelete : MutableLiveData<Boolean> = MutableLiveData()

    fun showNotes() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                repository.showNotes().let {
                    result.postValue(it)
                }
            }catch (e:Exception){

            }
        }
    }

    fun deleteNote(id:Int){
        viewModelScope.launch (Dispatchers.IO){
            try {
                repository.deleteNote(id).let {
                    resultDelete.postValue(true)
                }
            }catch (e:Exception){

            }
        }
    }
}