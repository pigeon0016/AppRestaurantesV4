package com.example.apprestaurantesv4

import com.google.gson.annotations.SerializedName

data class PasoERestaurantResponse(
    @SerializedName("restaurantes") var restaurantes : ArrayList<PasoFRestaurantes> = arrayListOf()
)
