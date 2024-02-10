package com.aliemressman.movieapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class DashboardUpcomVeriler (

    @ColumnInfo(name = "page")
    @SerializedName("page")
    val pageCount : Int?,
    @ColumnInfo(name = "results")
    @SerializedName("results")
    val results : List<UpcomResult>?,
    @ColumnInfo(name = "total_pages")
    @SerializedName("total_pages")
    val totalPages :  Int?,
    @ColumnInfo(name = "total_results")
    @SerializedName("total_results")
    val totalResults :  Int?
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
}