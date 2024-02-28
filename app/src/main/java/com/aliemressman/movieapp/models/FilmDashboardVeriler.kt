package com.aliemressman.movieapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
// Entity = Room içinde veritabanında oluşturulan bir tabloyu temsil eder.
// ColumnInfo = Tek sütundan fazla oluşturulmasını istiyorsanız başına eklemeniz gerekir.
// SerializedName = Jsondaki key değerinden farklı bir isim kullanacaksak kullanırız.
data class FilmDashboardVeriler(
    @ColumnInfo(name ="page")
    @SerializedName("page")
    val pageCount : Int?,
    @ColumnInfo(name = "results")
    @SerializedName("results")
    val results : List<DashboardResult>?,
    @ColumnInfo(name ="total_pages")
    @SerializedName("total_pages")
    val totalPages :  Int?,
    @ColumnInfo(name ="total_results")
    @SerializedName("total_results")
    val totalResults :  Int?
){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0
    // PrimaryKey = Veritabanında bir tablonun birincil anahtarını temsil eder.
    // autoGenerate = true = Otomatik artan bir sütun oluşturur.
}