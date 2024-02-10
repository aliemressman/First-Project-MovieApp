package com.aliemressman.movieapp.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliemressman.movieapp.ViewModel.DetailViewModel
import com.aliemressman.movieapp.adapter.DetailSirketAdapter
import com.aliemressman.movieapp.databinding.ActivityDetailBinding
import com.aliemressman.movieapp.models.FilmDetailVeriler
import com.aliemressman.movieapp.models.Production
import com.aliemressman.movieapp.util.gorselIndir
import com.aliemressman.movieapp.util.placeHolderYap

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var detailSirketAdapter = DetailSirketAdapter(ArrayList())
    var id = ""


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true)
        binding.detailRecyclerView.adapter = detailSirketAdapter

        intent.getStringExtra("FILMID")?.let {
            id = it
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.refreshData(id)
        observeLiveData()

        binding.backImage.setOnClickListener {
            onClickBack(id)
        }
    }

    private fun updateUI(film: FilmDetailVeriler){
        binding.movieNameTxt.text = film.filmBaslik
        binding.movieDateTxt.text = film.filmTarih
        binding.movieTimeTxt.text = film.filmSure
        binding.movieRateTxt.text = film.filmPuan
        binding.movieSummaryInfo.text = film.filmOzet
        val normalUrl = "https://image.tmdb.org/t/p/w500" + film.filmPoster
        binding.posterNormalImg.gorselIndir(normalUrl, placeHolderYap(binding.posterNormalImg.context))
        val bigUrl = "https://image.tmdb.org/t/p/w500" + film.filmBackPoster
        binding.posterBigImg.gorselIndir(bigUrl, placeHolderYap(binding.posterBigImg.context))
    }

    private fun checkIsNull(list: List<Production>):List<Production>{
        var newList = arrayListOf<Production>()
        list.forEach {
            if (!it.yapimciPoster.isNullOrEmpty()){
                newList.add(it)
            }
        }
        return newList
    }

    private fun observeLiveData() {
        viewModel.filmDetailVeriler.observe(this, Observer {
            it?.let {
                updateUI(it)
            }
        })

        viewModel.detayYukleniyor.observe(this, Observer {
            it?.let {
                if (it) {
                    binding.loading4.visibility = View.VISIBLE
                } else {
                    binding.loading4.visibility = View.GONE
                }
            }
        })
        //----------------
        viewModel.filmDetailSirketVeriler.observe(this, Observer {
            it?.let {
                Log.d("veri", it.toString())
                detailSirketAdapter.sirketListesiniGuncelle(checkIsNull(it))
            }
        })
        viewModel.detayYukleniyor.observe(this, Observer {
            it?.let {
                if (it){
                    binding.loading4.visibility = View.VISIBLE
                }
                else{
                    binding.loading4.visibility = View.GONE
                }
            }
        })
    }

    private fun onClickBack(filmId : String){
        val intent = Intent(this , DashboardActivity::class.java)
        intent.putExtra("FILMID",filmId)
        finish()
    }
}