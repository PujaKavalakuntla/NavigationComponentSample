package com.example.navigationcomponentsample.model

import com.google.gson.annotations.SerializedName

class Movies {
    @SerializedName("category")
    var category: String? = null

    @SerializedName("imageUrl")
    var imageUrl: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("desc")
    var desc: String? = null
}