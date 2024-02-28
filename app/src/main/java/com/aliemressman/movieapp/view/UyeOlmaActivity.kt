package com.aliemressman.movieapp.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aliemressman.movieapp.R
import com.aliemressman.movieapp.databinding.ActivityLoginBinding
import com.aliemressman.movieapp.databinding.ActivityUyeolmaBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UyeOlmaActivity : AppCompatActivity() {

    private val googleSingInRequestCode = 234
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityUyeolmaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUyeolmaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("122869650630-kpmf3pttogn81bb6e37qdk8a6s8lpgj4.apps.googleusercontent.com")
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        val googleGirisButon = binding.googleBtn
        googleGirisButon.setOnClickListener {
            if (isConnected(this)) {
                val signInIntent = googleSignInClient.signInIntent
                startActivityForResult(signInIntent, googleSingInRequestCode)
            } else {
                Toast.makeText(this, "İnternet Bağlantınızı Kontrol Ediniz", Toast.LENGTH_LONG).show()
            }
        }
    }

   fun isConnected(uyeOlmaActivity: UyeOlmaActivity): Boolean {
        val connectivityManager = uyeOlmaActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            googleSingInRequestCode -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account)
                } catch (e: ApiException) {
                    e.printStackTrace()
                    e.message?.let { Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(account : GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
               Toast.makeText(this, "Google Hesabı ile Giriş Yapıldı", Toast.LENGTH_LONG).show()
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                it.printStackTrace()
                it.message?.let { it1 -> Toast.makeText(this, it1, Toast.LENGTH_LONG).show() }
            }
    }

    fun uyeOlBtn(view : View) {
        val email = binding.epostaText.text.toString()
        val sifre = binding.sifreText.text.toString()
        if (email.isEmpty() || sifre.isEmpty()) {
            Toast.makeText(applicationContext, "Lütfen Bilgileri Tam Doldurunuz!", Toast.LENGTH_LONG).show()
            return
        } else {
            auth.createUserWithEmailAndPassword(email, sifre)
                //asekron bir işlem olduğu için addOnCompleteListener kullanılır. Asekron işlemler hemen sonuç döndürmezler .
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, LoginActivity::class.java)
                        Toast.makeText(applicationContext, "Kullanıcı Oluşturuldu", Toast.LENGTH_LONG).show()
                        startActivity(intent)
                        finish()
                    }
                }.addOnFailureListener { exception ->
                    //hata durumunda yapılacaklar
                    Toast.makeText(applicationContext,"Kullanıcı Oluşturulamadı", Toast.LENGTH_LONG).show()
                }
        }
    }
}