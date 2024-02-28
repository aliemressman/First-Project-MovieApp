package com.aliemressman.movieapp.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aliemressman.movieapp.models.DashboardResult
import com.aliemressman.movieapp.models.FilmDashboardVeriler
import com.aliemressman.movieapp.models.Genres
import com.aliemressman.movieapp.models.GenresVeriler
import com.aliemressman.movieapp.models.UpcomResult
import com.aliemressman.movieapp.models.UpcomVeriler

@Database(entities = [DashboardResult::class, UpcomResult::class , Genres::class], version = 2)

abstract class FilmDataBase : RoomDatabase() {
// Database = SQLite DB ile bağlantısı olan bir nesnedir. Tüm aksiyonlar bunun üzerinden gerçekleşir.
    abstract fun filmDao() : FilmDAO

    //singeleton
    companion object {
        //instance = bu sınıfın bir örneği oluşturulduğunda bu değişkenin değeri değişmez.
        // Volatile = bu değişkenin her zaman en güncel değeri okunur.
        @Volatile private var instance : FilmDataBase? = null
        private var lock = Any()
        operator fun invoke(context: Context) = instance?: synchronized(lock){
            instance?: dataBaseOlustur(context).also {
                instance = it
            }
        }
        private fun dataBaseOlustur(context : Context) = Room.databaseBuilder(
            context.applicationContext,
            FilmDataBase::class.java,"filmdatabase")
            .fallbackToDestructiveMigration()
            .build()
        // bu kod parçası, veritabanı oluşturulurken veritabanı şeması değişirse, veritabanı şemasını yedekleme işlemi yapar.
    }
}

