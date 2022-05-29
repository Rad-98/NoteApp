package com.example.note.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    var title:String?,
    var description:String?

)
