package binar.ganda.latihanchapter6.network

import binar.ganda.latihanchapter6.model.ResponseDataFilmItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("film")
    fun getAllFilm() : Call<List<ResponseDataFilmItem>>
}