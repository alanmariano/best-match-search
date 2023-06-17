package com.alan.searchengine.dto

data class RestaurantSearchParameters(
    val name: String?,
    val rating: Double?,
    val distance: Double?,
    val price: Double?,
    val cuisine: String?
)