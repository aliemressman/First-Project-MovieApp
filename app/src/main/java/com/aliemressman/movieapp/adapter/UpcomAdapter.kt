package com.aliemressman.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aliemressman.movieapp.R
import com.aliemressman.movieapp.models.UpcomResult
import com.aliemressman.movieapp.util.gorselIndir
import com.aliemressman.movieapp.util.placeHolderYap
interface OnFilmClickListenerr {
    fun onFilmClick(filmId: String)
}
class UpcomAdapter(val upcomListesi : ArrayList<UpcomResult> , private val listener: OnFilmClickListenerr) : RecyclerView.Adapter<UpcomAdapter.UpcomHolder>() {
    class UpcomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val baslikTextView = itemView.findViewById<TextView>(R.id.filmBaslık)
        val puanTextView = itemView.findViewById<TextView>(R.id.puanView)
        val posterImageView = itemView.findViewById<ImageView>(R.id.pic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.viewholder_film, parent, false)
        return UpcomHolder(view)
    }

    override fun getItemCount(): Int {
        return upcomListesi.size
    }

    override fun onBindViewHolder(holder: UpcomHolder, position: Int) {
        holder.baslikTextView.text = upcomListesi[position].filmBaslik
        holder.puanTextView.text = upcomListesi[position].filmPuan

        holder.itemView.setOnClickListener {
            upcomListesi[position].filmId?.let {
                listener.onFilmClick(it)
            }
        }
            val url = "https://image.tmdb.org/t/p/w500" + upcomListesi[position].filmPoster
            holder.posterImageView.gorselIndir(url, placeHolderYap(holder.posterImageView.context))
    }

    fun filmUpcomGuncelle(it: List<UpcomResult>) {
        upcomListesi.clear()
        upcomListesi.addAll(it)
        notifyDataSetChanged()

    }
}