package com.aliemressman.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aliemressman.movieapp.R
import com.aliemressman.movieapp.models.UpcomResult

class UpcomAdapter(val upcomListesi : ArrayList<UpcomResult>) : RecyclerView.Adapter<UpcomAdapter.UpcomHolder>() {
    class UpcomHolder (itemView : View): RecyclerView.ViewHolder(itemView){

        val baslikTextView = itemView.findViewById<TextView>(R.id.filmBaslık)
        val puanTextView = itemView.findViewById<TextView>(R.id.puanView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.viewholder_film,parent,false)
        return UpcomHolder(view)
    }

    override fun onBindViewHolder(holder: UpcomHolder, position: Int) {
        holder.baslikTextView.text = upcomListesi.get(position).filmBaslik
        holder.puanTextView.text = upcomListesi.get(position).filmPuan
    }

    override fun getItemCount(): Int {
        return upcomListesi.size
    }

    fun filmUpcomGuncelle(yeniUpcomListesi: List<UpcomResult>){
        upcomListesi.clear()
        upcomListesi.addAll(yeniUpcomListesi)
        notifyDataSetChanged()
    }
}