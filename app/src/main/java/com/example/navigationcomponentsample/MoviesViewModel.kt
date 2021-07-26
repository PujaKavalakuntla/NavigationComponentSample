package com.example.navigationcomponentsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationcomponentsample.model.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel constructor(private val repository: MoviesRepository):ViewModel() {
    val movieList = MutableLiveData<List<Movies>>()
    val errorMessage = MutableLiveData<String>()
    fun getAllMovies() {
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<List<Movies>> {
            override fun onResponse(call: Call<List<Movies>>, response: Response<List<Movies>>) {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Movies>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}