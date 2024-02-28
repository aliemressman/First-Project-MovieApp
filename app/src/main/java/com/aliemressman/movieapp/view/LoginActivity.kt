package com.aliemressman.movieapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.aliemressman.movieapp.R
import com.aliemressman.movieapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

    }
    fun girisYap(view: View){
         val email = binding.epostaText.text.toString()
         val sifre = binding.sifreText.text.toString()
        if (email.isEmpty() || sifre.isEmpty()) {
            Toast.makeText(applicationContext, "Eposta ve Şifre Giriniz", Toast.LENGTH_LONG).show()
            return
        }
        auth.signInWithEmailAndPassword(binding.epostaText.text.toString(), binding.sifreText.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //diğer aktiviteye geçiş yapılacak
                    val guncelKullanici = auth.currentUser?.email.toString()
                    Toast.makeText(applicationContext, "Hoşgeldin $guncelKullanici", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                //hata durumunda yapılacaklar
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
    }
    fun uyeOl(view : View) {
        val intent = Intent(this, UyeOlmaActivity::class.java)
        Toast.makeText(applicationContext, "Üyeliğe Yönlendiriliyorsunuz", Toast.LENGTH_LONG).show()
        startActivity(intent)
        finish()
    }
}