package com.alan.searchengine.dto

class Restaurant(
    val name: String,
    val rating: Double,
    val distance: Double,
    val price: Double,
    val cuisine: Cuisine?,
    var matchLevel: Double = 0.0
)