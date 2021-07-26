package com.example.navigationcomponentsample.model

import com.google.gson.annotations.SerializedName

class Birds {
    @SerializedName("id")
    var id = 0

    @SerializedName("birds_image")
    var birdsImageUrl: String? = null

    @SerializedName("birds_name")
    var birdsName: String? = null
}