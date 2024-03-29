package com.androiddevelopers.villabuluyorum.model

data class House(
    val name: String,
    val title: String,
    val address: String,
    val distance: String,
    val bedrooms: Int,
    val bathrooms: Int,
    val areaSquareMeters: Double,
    val rentPrice: Double,
    val description : String,
    val mainImage : String,
    val images : List<String>,
    val leaseDurationMonths: Int
)