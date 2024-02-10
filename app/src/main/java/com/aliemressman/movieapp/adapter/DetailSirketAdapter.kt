package com.aliemressman.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.aliemressman.movieapp.R
import com.aliemressman.movieapp.models.Production
import com.aliemressman.movieapp.util.gorselIndir
import com.aliemressman.movieapp.util.placeHolderYap

class DetailSirketAdapter(val sirketListesi : ArrayList<Production>) : RecyclerView.Adapter<DetailSirketAdapter.DetailHolder>() {
    class DetailHolder (view : View): RecyclerView.ViewHolder(view){

        val sirketImageView = view.findViewById<ImageView>(R.id.itemImages)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.viewholder_detail_oyuncu,parent,false)
        return DetailHolder(view)
    }

    override fun getItemCount(): Int {
        return sirketListesi.size
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {

        val url ="https://image.tmdb.org/t/p/w500" + sirketListesi.get(position).yapimciPoster
        holder.sirketImageView.gorselIndir(url, placeHolderYap(holder.sirketImageView.context))
    }

    fun sirketListesiniGuncelle(yeniSirketListesi : List<Production>){
        sirketListesi.clear()
        sirketListesi.addAll(yeniSirketListesi)
        notifyDataSetChanged()
    }
}
