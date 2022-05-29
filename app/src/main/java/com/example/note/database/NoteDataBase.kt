package com.example.note.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.note.database.dao.NoteDao
import com.example.note.database.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: NoteDataBase? = null
        fun getInstance(context: Context): NoteDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    NoteDataBase::class.java, "note.db"
                ).fallbackToDestructiveMigration().build()
            }
            return instance as NoteDataBase
        }
    }


}