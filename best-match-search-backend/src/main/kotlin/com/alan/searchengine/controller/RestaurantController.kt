package com.alan.searchengine.controller

import com.alan.searchengine.dto.Restaurant
import com.alan.searchengine.dto.RestaurantSearchParameters
import com.alan.searchengine.service.RestaurantService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/restaurants")
class RestaurantController(
    private val restaurantService: RestaurantService
) : ControllerInterface<Restaurant>(restaurantService) {
    @GetMapping("/search")
    fun getRestaurantMatches(@RequestParam name: String,
                             @RequestParam(required = false) rating: Double?,
                             @RequestParam(required = false) distance: Double?,
                             @RequestParam(required = false) price: Double?,
                             @RequestParam(required = false) cuisine: String?) : List<Restaurant> {


        return restaurantService.getRestaurantMatches(
            RestaurantSearchParameters(
                name,
                rating,
                distance,
                price,
                cuisine
            )
        )

    }

}