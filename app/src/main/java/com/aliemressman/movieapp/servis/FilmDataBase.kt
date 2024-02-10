package com.aliemressman.movieapp.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FilmAPI::class], version = 1)
abstract class FilmDataBase : RoomDatabase() {

    abstract fun filmDao() : FilmDAO

    companion object {

        @Volatile private var instance : FilmDataBase? = null

        private var lock = Any()

        operator fun invoke(context: Context) = instance?: synchronized(lock){
            instance?: dataBaseOlustur(context).also {
                instance = it
            }
        }

        private fun dataBaseOlustur(context : Context) = Room.databaseBuilder(
            context.applicationContext,
            FilmDataBase::class.java,"filmdatabase").build()
    }
}

