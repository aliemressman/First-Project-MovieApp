package com.aliemressman.movieapp.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.aliemressman.movieapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.gorselIndir(url : String? , placeholder : CircularProgressDrawable){

    // Glide, resimleri yüklemek için kullanılan bir kütüphanedir. Bu kütüphane, resimleri yüklerken hafızayı ve disk önbelleğini kullanarak yükler.
    // fotoğrafın yüklenme esnasında gösterilecek olan resim.
    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}
fun placeHolderYap(context : Context) : CircularProgressDrawable{
    //placeholder, resim yüklenirken gösterilecek olan resimdir.
    return CircularProgressDrawable(context).apply {
        // CircularProgressDrawable, dönen bir progress bar oluşturur.
        strokeWidth = 8f
        // progress barın kalınlığı
        centerRadius = 40f
        // progress barın yarıçapı
        start()
    }
}

