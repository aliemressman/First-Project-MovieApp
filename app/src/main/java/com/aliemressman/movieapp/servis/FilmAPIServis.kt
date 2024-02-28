package com.aliemressman.movieapp.servis

import com.aliemressman.movieapp.models.FilmDashboardVeriler
import com.aliemressman.movieapp.models.FilmDetailVeriler
import com.aliemressman.movieapp.models.GenresVeriler
import com.aliemressman.movieapp.models.UpcomVeriler
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FilmAPIServis {

    //private val API_KEY ="04759f590216a5a7c4b7d7b74f4537cf"
    private val BASE_URL = "https://api.themoviedb.org/"
   // private val POSTER_BASE_URL ="/7lTnXOy0iNtBAdRP3TZvaKJ77F6.jpg"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(FilmAPI::class.java)

    fun getFilm() : Single<FilmDashboardVeriler>{
        return api.getFilm()
    }

    fun getGenres() : Single<GenresVeriler>{
        return api.getGenres()
    }

    fun getUpcom() : Single<UpcomVeriler>{
        return api.getUpcom()
    }

    fun getDetail(id: String) : Single<FilmDetailVeriler>{
        return api.getDetail(id)
    }
}