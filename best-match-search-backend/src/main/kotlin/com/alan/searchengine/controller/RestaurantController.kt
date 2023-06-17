package com.alan.searchengine.controller

import com.alan.searchengine.dto.Restaurant
import com.alan.searchengine.dto.RestaurantSearchParameters
import com.alan.searchengine.service.RestaurantService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/restaurants")
class RestaurantController (
    private val restaurantService: RestaurantService
) {

    @GetMapping("/names")
    fun getRestaurantNames() : List<String> = restaurantService.getRestaurantNames()

    @GetMapping("/{name}/{rating}/{distance}/{price}/{cuisine}")
    fun getRestaurantMatches(@PathVariable name: String,
                  @PathVariable rating: Double,
                  @PathVariable distance: Double,
                  @PathVariable price: Double,
                  @PathVariable cuisine: String) : List<Restaurant> {
        return restaurantService.getRestaurantMatches(RestaurantSearchParameters(name, rating, distance, price, cuisine))
    }

}