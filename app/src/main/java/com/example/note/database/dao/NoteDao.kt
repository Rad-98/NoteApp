package com.example.note.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.note.database.entity.NoteEntity

@Dao
interface NoteDao {

    @Update
    fun edit(noteEntity: NoteEntity)

    @Insert
    fun addNote(noteEntity: NoteEntity)

    @Query("select * from tbl_note")
    fun getNotes():List<NoteEntity>

    @Query("delete from tbl_note where id= :id")
    fun deleteNoteById(id:Int)






}