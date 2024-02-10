package com.aliemressman.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aliemressman.movieapp.R
import com.aliemressman.movieapp.models.Genres

class GenresAdapter(val turListesi : ArrayList<Genres>) : RecyclerView.Adapter<GenresAdapter.GenresHolder>() {
    class GenresHolder (itemView : View): RecyclerView.ViewHolder(itemView){

        val genresTextView = itemView.findViewById<TextView>(R.id.genresText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.dashboard_genres,parent,false)
        return GenresHolder(view)
    }

    override fun getItemCount(): Int {
        return turListesi.size
    }

    override fun onBindViewHolder(holder: GenresHolder, position: Int) {
        holder.genresTextView.text = turListesi.get(position).filmName
    }


    fun turListesiniGuncelle(yeniTurListesi :List<Genres>){
        turListesi.clear()
        turListesi.addAll(yeniTurListesi)
        notifyDataSetChanged()
    }
}

