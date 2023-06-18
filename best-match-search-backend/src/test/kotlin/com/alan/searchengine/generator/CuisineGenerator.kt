@file:JvmName("CuisineGenerator")
@file:JvmMultifileClass

package com.alan.searchengine.generator

import com.alan.searchengine.dto.Cuisine

fun generateCuisines(): List<Cuisine> {
    return listOf(
        Cuisine(1, "American"),
        Cuisine(2, "Chinese"),
        Cuisine(3, "Thai"),
        Cuisine(4, "Italian"),
        Cuisine(5, "French"),
        Cuisine(6, "Japanese")
    )
}

fun generateCuisinesMapById(): Map<Long, Cuisine> {
    return mapOf(
        Pair(1, Cuisine(1, "American")),
        Pair(2, Cuisine(2, "Chinese")),
        Pair(3, Cuisine(3, "Thai")),
        Pair(4, Cuisine(4, "Italian")),
        Pair(5, Cuisine(5, "French")),
        Pair(6, Cuisine(6, "Japanese"))
    )
}