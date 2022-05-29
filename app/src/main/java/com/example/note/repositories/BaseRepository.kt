package com.example.note.repositories

import com.example.note.MainApplication.Companion.appContext
import com.example.note.api.AppApi
import com.example.note.api.RetrofitHelper
import com.example.note.database.NoteDataBase


open class BaseRepository  {
    protected  var  noteDb : NoteDataBase = NoteDataBase.getInstance(appContext)
    protected  var  appApi : AppApi = RetrofitHelper.getInstanceQoute().create(AppApi::class.java)
    protected  var  newsApi : AppApi = RetrofitHelper.getInstanceNews().create(AppApi::class.java)


}