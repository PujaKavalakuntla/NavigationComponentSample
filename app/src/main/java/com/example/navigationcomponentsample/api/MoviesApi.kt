package com.example.navigationcomponentsample.api

import com.example.navigationcomponentsample.model.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MoviesApi {
    @GET("movielist.json")
    fun getAllMovies() : Call<List<Movies>>

    companion object {

        var moviesApi: MoviesApi? = null

        fun getInstance() : MoviesApi {

            if (moviesApi == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                moviesApi = retrofit.create(MoviesApi::class.java)
            }
            return moviesApi!!
        }
    }
}