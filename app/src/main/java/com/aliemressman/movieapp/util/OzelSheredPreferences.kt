package com.aliemressman.movieapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class OzelSheredPreferences {


    companion object{
        private val ZAMAN = "zaman"
        private var sharedPreferences : SharedPreferences? =null

        @Volatile private var instance : OzelSheredPreferences ?= null
        private val lock = Any()
        operator fun invoke(context : Context)  : OzelSheredPreferences = instance ?: synchronized(lock){
            instance ?: ozelSheredPreferencesYap(context).also {
                instance = it
            }
        }

        private fun ozelSheredPreferencesYap(context: Context): OzelSheredPreferences{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return OzelSheredPreferences()
        }
    }
    fun zamaniKaydet(zaman : Long){
        sharedPreferences?.edit(commit = true){
            putLong(ZAMAN,zaman)
        }
    }

    fun zamaniAl() = sharedPreferences?.getLong(ZAMAN,0)
}