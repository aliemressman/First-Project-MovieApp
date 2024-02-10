package com.aliemressman.movieapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aliemressman.movieapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.baslatButton.setOnClickListener {
            start()
        }
    }
    fun start(){
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }
}