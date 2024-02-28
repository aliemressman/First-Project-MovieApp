package com.aliemressman.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aliemressman.movieapp.R
import com.aliemressman.movieapp.models.DashboardResult
import com.aliemressman.movieapp.util.gorselIndir
import com.aliemressman.movieapp.util.placeHolderYap

interface OnFilmClickListener {
    fun onFilmClick(filmId: String)
}

class FilmAdapter(val filmListesi : ArrayList<DashboardResult>, private val listener: OnFilmClickListener) : RecyclerView.Adapter<FilmAdapter.FilmAdapter>() {
    class FilmAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val baslikTextView = itemView.findViewById<TextView>(R.id.filmBaslık)
        val puanTextView = itemView.findViewById<TextView>(R.id.puanView)
        val posterImageView = itemView.findViewById<ImageView>(R.id.pic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.viewholder_film, parent, false)
        return FilmAdapter(view)
    }

    override fun getItemCount(): Int {
        return filmListesi.size
    }

    override fun onBindViewHolder(holder: FilmAdapter, position: Int) {
        holder.baslikTextView.text = filmListesi[position].filmBaslik
        holder.puanTextView.text = filmListesi[position].filmPuan

        holder.itemView.setOnClickListener {
            filmListesi[position].filmId?.let {
                listener.onFilmClick(it)
            }
        }
        // Görselin linkini birbirine bağladık
        val url = "https://image.tmdb.org/t/p/w500" + filmListesi[position].filmPoster
        holder.posterImageView.gorselIndir(url, placeHolderYap(holder.posterImageView.context))

    }

    fun filmListesiniGuncelle(yeniFilmListesi: List<DashboardResult>) {
        filmListesi.clear()
        filmListesi.addAll(yeniFilmListesi)
        notifyDataSetChanged()
    }
}