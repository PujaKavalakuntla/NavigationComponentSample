package com.example.navigationcomponentsample

import com.example.navigationcomponentsample.api.MoviesApi

class MoviesRepository constructor(private val moviesApi: MoviesApi) {
    fun getAllMovies() = moviesApi.getAllMovies()
}