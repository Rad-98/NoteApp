package com.example.note.repositories

import com.example.note.database.entity.NoteEntity

class AddOrEditNoteRepository(): BaseRepository() {


    suspend fun addNote (note: NoteEntity){
        noteDb.noteDao().addNote(note)
    }

    suspend fun updateNote(note: NoteEntity) {
        return noteDb.noteDao().edit(note)
    }



}