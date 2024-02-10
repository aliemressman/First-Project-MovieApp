package com.aliemressman.movieapp.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aliemressman.movieapp.models.DashboardResult
import com.aliemressman.movieapp.models.DashboardUpcomVeriler
import com.aliemressman.movieapp.models.FilmDashboardVeriler
import com.aliemressman.movieapp.models.Genres
import com.aliemressman.movieapp.models.GenresVeriler
import com.aliemressman.movieapp.models.UpcomResult

@Dao
interface FilmDAO {

    @Insert
    suspend fun insertAll(vararg dashboardResult : DashboardResult) : List<Long>

    @Query("SELECT * FROM dashboardresult")
    suspend fun getAllFilm() : List<DashboardResult>

    @Query("SELECT * FROM dashboardresult WHERE uuid = :filmID")
    suspend fun getFilm(filmID : Int) : DashboardResult

    @Query("DELETE FROM dashboardresult")
    suspend fun deleteAllFilm()

    //-------------------------------------------------

    @Insert
    suspend fun insertGenresAll(vararg genres: Genres ) : List<Long>

    @Query("SELECT * FROM genresveriler")
    suspend fun getAllGenres() : List<Genres>

    @Query("SELECT * FROM genresveriler WHERE uuid = :filmID")
    suspend fun getGenres(filmID : Int) : Genres

    @Query("DELETE FROM genresveriler")
    suspend fun deleteGenresAllFilm()

    //--------------------------------------------------

    @Insert
    suspend fun insertUpcomAll(vararg film : UpcomResult) : List<Long>

    @Query("SELECT * FROM filmdashboardveriler")
    suspend fun getAllUpcom() : List<UpcomResult>

    @Query("SELECT * FROM filmdashboardveriler WHERE uuid = :filmID")
    suspend fun getUpcom(filmID : Int) : UpcomResult

    @Query("DELETE FROM filmdashboardveriler")
    suspend fun deleteUpcomAllFilm()
}