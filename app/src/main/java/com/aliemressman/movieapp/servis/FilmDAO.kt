package com.aliemressman.movieapp.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aliemressman.movieapp.models.DashboardResult
import com.aliemressman.movieapp.models.Genres
import com.aliemressman.movieapp.models.UpcomResult

@Dao
// DAO’lar, Room’u çalıştırırken verilerinize erişmek için kullanılır. Her DAO, verilerinizi işlemek için bir yöntem içermesi gerekir.
// Bu yöntemler, veriyi eklemek için INSERT, veriyi getirmek ve silmesi @Query’dir. Yani ekleme, güncelleme, silme.
// Vararg tüm anlamına gelir.
interface FilmDAO {

    @Insert
    suspend fun insertAll(vararg dashboardResult : DashboardResult) : List<Long>
    // list<Long> = insert edilen verinin id'sini döndürür.

    @Query("SELECT * FROM dashboardresult")
    suspend fun getAllFilm() : List<DashboardResult>
    // list<DashboardResult> = tüm filmleri getirir.

    @Query("SELECT * FROM dashboardresult WHERE uuid = :filmID")
    suspend fun getFilmID(filmID : Int) : DashboardResult
    // dashboardresult tablosundan uuid'si filmID olan filmi getirir.

    @Query("DELETE FROM dashboardresult")
    suspend fun deleteAllFilm()

    //-------------------------------------------------

    @Insert
    suspend fun insertGenresAll(vararg genres : Genres) : List<Long>

    @Query("SELECT * FROM genres")
    suspend fun getAllGenres() : List<Genres>

    @Query("SELECT * FROM genres WHERE uuid = :filmID")
    suspend fun getGenresID(filmID : Int) : Genres

    @Query("DELETE FROM genres")
    suspend fun deleteGenresAllFilm()

    //--------------------------------------------------


    @Insert
    suspend fun insertUpcomAll(vararg upcomresult : UpcomResult) : List<Long>

    @Query("SELECT * FROM upcomresult")
    suspend fun getAllUpcom() : List<UpcomResult>

    @Query("SELECT * FROM upcomresult WHERE uuid = :filmID")
    suspend fun getUpcomID(filmID : Int) : UpcomResult

    @Query("DELETE FROM upcomresult")
    suspend fun deleteUpcomAllFilm()


}