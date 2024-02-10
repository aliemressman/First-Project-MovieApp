package com.aliemressman.movieapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliemressman.movieapp.ViewModel.DashboardViewModel
import com.aliemressman.movieapp.adapter.FilmAdapter
import com.aliemressman.movieapp.adapter.GenresAdapter
import com.aliemressman.movieapp.adapter.OnFilmClickListener
import com.aliemressman.movieapp.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity(), OnFilmClickListener {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var viewModel : DashboardViewModel
    private var filmRecyclerAdapter = FilmAdapter(ArrayList(), this)

    private var genresAdapter = GenresAdapter(ArrayList())

    private var upcomAdapter = FilmAdapter(ArrayList(), this)

    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recommendedRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.recommendedRecyclerView.adapter = filmRecyclerAdapter

        binding.genresRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true)
        binding.genresRecyclerView.adapter = genresAdapter

        binding.upcomRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true)
        binding.upcomRecyclerView.adapter = upcomAdapter



        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        viewModel.refreshData()

        observeLiveData()

        Toast.makeText(this, "Hoşgeldiniz",Toast.LENGTH_SHORT).show()

        intent.getStringExtra("FILMID")?.let {
            id = it
        }
    }

    private fun observeLiveData(){
        // YeniFilm kısmının kodları
        viewModel.dashboardResultVeriler.observe(this, Observer {
            it?.let {
                filmRecyclerAdapter.filmListesiniGuncelle(it)
            }
        })

        viewModel.detayYukleniyor.observe(this, Observer {
            it?.let {
                if (it) {
                    binding.loading1.visibility = View.VISIBLE
                }
                else {
                    binding.loading1.visibility = View.GONE
                }
            }
        })

        // Genres Kısmının kodları.
        viewModel.dashBoardTurVeriler.observe(this, Observer {
            it?.let {
                genresAdapter.turListesiniGuncelle(it)
            }
        })
        viewModel.detayYukleniyor.observe(this, Observer {
            it?.let {
                if (it){
                    binding.loading2.visibility = View.VISIBLE
                }
                else{
                    binding.loading2.visibility = View.GONE
                }
            }
        })

        // GelecekFilm kısmının kodları

        viewModel.dashboardUpcomVeriler.observe(this, Observer {
            it?.let {
                upcomAdapter.filmListesiniGuncelle(it)
            }
        })
        viewModel.detayYukleniyor.observe(this, Observer {
            it?.let {
                if (it){
                    binding.loading3.visibility = View.VISIBLE
                }
                else{
                    binding.loading3.visibility = View.GONE
                }
            }
        })
    }

    override fun onFilmClick(filmId: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("FILMID", filmId)
        startActivity(intent)
    }
}