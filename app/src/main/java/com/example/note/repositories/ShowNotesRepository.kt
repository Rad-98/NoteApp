package com.example.note.repositories

import com.example.note.database.entity.NoteEntity

class ShowNotesRepository() :BaseRepository() {

    suspend fun showNotes():List<NoteEntity>{
        return noteDb.noteDao().getNotes()
    }

    suspend fun deleteNote(id:Int){
        return noteDb.noteDao().deleteNoteById(id)
    }



}