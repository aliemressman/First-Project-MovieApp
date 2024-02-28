package com.aliemressman.movieapp.servis

import androidx.room.Entity
import com.aliemressman.movieapp.models.FilmDashboardVeriler
import com.aliemressman.movieapp.models.FilmDetailVeriler
import com.aliemressman.movieapp.models.GenresVeriler
import com.aliemressman.movieapp.models.UpcomVeriler
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmAPI {

    @GET("3/movie/popular?api_key=04759f590216a5a7c4b7d7b74f4537cf")
    fun getFilm() : Single<FilmDashboardVeriler>
    // Single = RxJava'da kullanılan bir yapıdır. Bu yapı, bir veri akışı oluşturur ve bu veri akışı üzerinde işlemler yapılmasını sağlar.

    @GET("3/genre/movie/list?api_key=04759f590216a5a7c4b7d7b74f4537cf")
    fun getGenres() : Single<GenresVeriler>

    @GET("3/movie/upcoming?api_key=04759f590216a5a7c4b7d7b74f4537cf")
    fun getUpcom() : Single<UpcomVeriler>

    @GET("3/movie/{movieid}?api_key=04759f590216a5a7c4b7d7b74f4537cf")
    fun getDetail(@Path("movieid") id: String) : Single<FilmDetailVeriler>
}