package binar.ganda.latihanchapter6.viewmodel

import binar.ganda.latihanchapter6.model.ResponseDataFilmItem
import binar.ganda.latihanchapter6.network.RetrofitApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmViewModel : ViewModel() {

    lateinit var liveDataFilm : MutableLiveData<List<ResponseDataFilmItem>>

    init {
        liveDataFilm = MutableLiveData()
    }

    fun getLivedataFilm() : MutableLiveData<List<ResponseDataFilmItem>> {
        return liveDataFilm
    }

    fun callApiFilm() {
        with(RetrofitApi) {
            INSTANCE.getAllFilm()
                .enqueue(object : Callback<List<ResponseDataFilmItem>>{
                    override fun onResponse(
                        call: Call<List<ResponseDataFilmItem>>,
                        response: Response<List<ResponseDataFilmItem>>
                    ) {
                        if (response.isSuccessful){
                            liveDataFilm.postValue(response.body())
                        } else{
                            liveDataFilm.postValue(null)
                        }
                    }

                    override fun onFailure(call: Call<List<ResponseDataFilmItem>>, t: Throwable) {
                        liveDataFilm.postValue(null)
                    }

                })
        }
    }
}
