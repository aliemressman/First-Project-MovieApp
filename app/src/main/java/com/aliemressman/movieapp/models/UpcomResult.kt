package com.aliemressman.movieapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
class UpcomResult (
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val filmId : String?,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val filmBaslik : String?,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val filmPoster :  String?,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val filmPuan :  String?,
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}
