package com.example.navigationcomponentsample.api

import com.example.navigationcomponentsample.model.Birds
import retrofit2.Call
import retrofit2.http.GET
import java.util.ArrayList

interface BirdsApi {
    @GET("birdsdata.php")
    fun geBirdsData(): Call<ArrayList<Birds?>?>?

    companion object {
        const val BASE_URL = "https://uniqueandrocode.000webhostapp.com/hiren/androidtutorial/"
    }
}