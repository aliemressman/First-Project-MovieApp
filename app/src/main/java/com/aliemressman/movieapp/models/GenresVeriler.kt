package com.aliemressman.movieapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GenresVeriler(
    @ColumnInfo(name = "genres")
    @SerializedName("genres")
    val genres : List<Genres>
){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}

@Entity
data class Genres(
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val filmId : String?,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val filmName : String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}

