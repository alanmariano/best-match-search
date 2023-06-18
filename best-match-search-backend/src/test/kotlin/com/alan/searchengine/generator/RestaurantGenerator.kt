@file:JvmName("RestaurantGenerator")
@file:JvmMultifileClass

package com.alan.searchengine.generator

import com.alan.searchengine.dto.Cuisine
import com.alan.searchengine.dto.Restaurant

fun generateRestaurants(): List<Restaurant> {

    return listOf(
        Restaurant("Hut Chow", 3.0,2.0,10.0, Cuisine(3, "Thai")),
        Restaurant("Wish Chow", 3.0,1.0,40.0,Cuisine(1, "American")),
        Restaurant("Chowish",3.0,2.0,10.0,Cuisine(6, "Japanese")),
        Restaurant("Deliciousbea",1.0,6.0,50.0,Cuisine(1, "American")),
        Restaurant("Deliciousquipo",5.0,3.0,10.0,Cuisine(6, "Japanese")),
        Restaurant("Fed Delicious",3.0,9.0,35.0,Cuisine(4, "Italian")),
        Restaurant("Hotspot Delicious",4.0,10.0,25.0,Cuisine(1, "American")),
        Restaurant("Gusto Delicious",5.0,3.0,50.0,Cuisine(2, "Chinese")),
        Restaurant("Deliciouszen",2.0,2.0,30.0,Cuisine(5, "French")),
        Restaurant("Deliciouszoid",3.0,2.0,30.0,Cuisine(4, "Italian"))
    )

}