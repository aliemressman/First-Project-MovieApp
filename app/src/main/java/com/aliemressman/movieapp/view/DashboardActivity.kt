package com.aliemressman.movieapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliemressman.movieapp.R
import com.aliemressman.movieapp.ViewModel.DashboardViewModel
import com.aliemressman.movieapp.adapter.FilmAdapter
import com.aliemressman.movieapp.adapter.GenresAdapter
import com.aliemressman.movieapp.adapter.OnFilmClickListener
import com.aliemressman.movieapp.adapter.OnFilmClickListenerr
import com.aliemressman.movieapp.adapter.UpcomAdapter
import com.aliemressman.movieapp.databinding.ActivityDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log


class DashboardActivity : AppCompatActivity(), OnFilmClickListener , OnFilmClickListenerr {

    private companion object{
        private const val TAG = "DashboardActivity"
    }

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var viewModel: DashboardViewModel
    private var filmRecyclerAdapter = FilmAdapter(ArrayList(), this)

    private var genresAdapter = GenresAdapter(ArrayList())

    private var upcomAdapter = UpcomAdapter(ArrayList(), this)

    var id = ""

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recommendedRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.recommendedRecyclerView.adapter = filmRecyclerAdapter

        binding.genresRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.genresRecyclerView.adapter = genresAdapter

        binding.upcomRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.upcomRecyclerView.adapter = upcomAdapter

        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        viewModel.refreshFromInternet()

        //TODO: SwipeRefreshLayout Eklenecek.
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.hataMesaji.value = false
            viewModel.detayYukleniyor.value = true
            viewModel.refreshFromInternet()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
        Toast.makeText(this, "İyi Eğlenceler!", Toast.LENGTH_SHORT).show()
        intent.getStringExtra("FILMID")?.let {
            id = it
        }
        auth = FirebaseAuth.getInstance()

        binding.backLogin.setOnClickListener {
            backLogin()
        }
    }

    private fun observeLiveData() {
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
                } else {
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
                if (it) {
                    binding.loading2.visibility = View.VISIBLE
                } else {
                    binding.loading2.visibility = View.GONE
                }
            }
        })

        // GelecekFilm kısmının kodları

        viewModel.dashboardUpcomVeriler.observe(this, Observer {
            it?.let {
                upcomAdapter.filmUpcomGuncelle(it)
            }
        })
        viewModel.detayYukleniyor.observe(this, Observer {
            it?.let {
                if (it) {
                    binding.loading3.visibility = View.VISIBLE
                } else {
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
    fun backLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}