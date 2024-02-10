package com.aliemressman.movieapp.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliemressman.movieapp.models.FilmDetailVeriler
import com.aliemressman.movieapp.models.Production
import com.aliemressman.movieapp.servis.FilmAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DetailViewModel : ViewModel() {

    val detayYukleniyor = MutableLiveData<Boolean>()
    val hataMesaji = MutableLiveData<Boolean>()
    val filmDetailVeriler = MutableLiveData<FilmDetailVeriler>()
    val filmDetailSirketVeriler = MutableLiveData<List<Production>>()

    private val filmAPIServis = FilmAPIServis()
    private val disposable = CompositeDisposable()

    fun refreshData(id: String){
        getDetail(id)
    }

    private fun getDetail(id: String){
        detayYukleniyor.value = true

        disposable.add(
            filmAPIServis.getDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<FilmDetailVeriler>(){
                    override fun onSuccess(t: FilmDetailVeriler) {
                        Log.d("detaill", t.toString())
                        filmDetailVeriler.value = (t)
                        filmDetailSirketVeriler.value = t.filmYapimci
                        detayYukleniyor.value = false
                        hataMesaji.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.d("detaill", "hata")
                        hataMesaji.value = true
                        detayYukleniyor.value = false
                        e.printStackTrace()
                    }

                })
        )
    }
}

