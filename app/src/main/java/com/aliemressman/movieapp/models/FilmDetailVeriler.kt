package com.aliemressman.movieapp.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class FilmDetailVeriler (
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val filmId : String?,

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    val filmBaslik : String?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val filmPoster :  String?,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val filmBackPoster :  String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val filmOzet : String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val filmPuan :  String?,

    @ColumnInfo(name = "runtime")
    @SerializedName("runtime")
    val filmSure : String?,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val filmTarih : String?,

    @ColumnInfo(name = "production_companies")
    @SerializedName("production_companies")
    val filmYapimci : List<Production>,
    )
{
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}

@Entity
data class Production(
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id :String?,

    @ColumnInfo(name = "logo_path")
    @SerializedName("logo_path")
    val yapimciPoster : String?,
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}