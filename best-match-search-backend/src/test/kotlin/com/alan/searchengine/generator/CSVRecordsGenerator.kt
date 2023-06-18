@file:JvmName("CSVRecordsGenerator")
@file:JvmMultifileClass

package com.alan.searchengine.generator

fun generateRestaurantRecords(): List<Map<String, String>> {

    return listOf(
        mapOf(Pair("name", "Hut Chow"),Pair("customer_rating", "3"),Pair("distance", "2"),Pair("price", "10"),Pair("cuisine_id", "3")),
        mapOf(Pair("name", "Wish Chow"),Pair("customer_rating", "3"),Pair("distance", "1"),Pair("price", "40"),Pair("cuisine_id", "1")),
        mapOf(Pair("name", "Chowish"),Pair("customer_rating", "3"),Pair("distance", "2"),Pair("price", "10"),Pair("cuisine_id", "6")),
        mapOf(Pair("name", "Deliciousbea"),Pair("customer_rating", "1"),Pair("distance", "6"),Pair("price", "50"),Pair("cuisine_id", "1")),
        mapOf(Pair("name", "Deliciousquipo"),Pair("customer_rating", "5"),Pair("distance", "3"),Pair("price", "10"),Pair("cuisine_id", "6")),
        mapOf(Pair("name", "Fed Delicious"),Pair("customer_rating", "3"),Pair("distance", "9"),Pair("price", "35"),Pair("cuisine_id", "4")),
        mapOf(Pair("name", "Hotspot Delicious"),Pair("customer_rating", "4"),Pair("distance", "10"),Pair("price", "25"),Pair("cuisine_id", "1")),
        mapOf(Pair("name", "Gusto Delicious"),Pair("customer_rating", "5"),Pair("distance", "3"),Pair("price", "50"),Pair("cuisine_id", "2")),
        mapOf(Pair("name", "Deliciouszen"),Pair("customer_rating", "2"),Pair("distance", "2"),Pair("price", "30"),Pair("cuisine_id", "5")),
        mapOf(Pair("name", "Deliciouszoid"),Pair("customer_rating", "3"),Pair("distance", "2"),Pair("price", "30"),Pair("cuisine_id", "4"))
    )

}

fun generateCuisineRecords(): List<Map<String, String>> {

    return listOf(
        mapOf(Pair("id", "1"), Pair("name", "American")),
        mapOf(Pair("id", "2"), Pair("name", "Chinese")),
        mapOf(Pair("id", "3"), Pair("name", "Thai")),
        mapOf(Pair("id", "4"), Pair("name", "Italian")),
        mapOf(Pair("id", "5"), Pair("name", "French")),
        mapOf(Pair("id", "6"), Pair("name", "Japanese")),
    )

}