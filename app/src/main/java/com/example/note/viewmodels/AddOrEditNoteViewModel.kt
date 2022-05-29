package com.example.note.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.note.database.entity.NoteEntity
import com.example.note.repositories.AddOrEditNoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddOrEditNoteViewModel constructor(application: Application) : AndroidViewModel(application) {

    private val repository: AddOrEditNoteRepository = AddOrEditNoteRepository()
    val isOk: MutableLiveData<Boolean> = MutableLiveData()

    fun addNote(noteEntity: NoteEntity) {

        viewModelScope.launch(Dispatchers.IO) {
            if (noteEntity.id >= 0) {
                try {
                    repository.addNote(noteEntity)
                    isOk.postValue(true)
                } catch (e: Exception) {
                    isOk.postValue(false)
                }
            }
        }
    }

    fun editNote(noteEntity: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateNote(noteEntity)
                isOk.postValue(true)
            } catch (e: Exception) {
                isOk.postValue(false)
            }
        }
    }


}