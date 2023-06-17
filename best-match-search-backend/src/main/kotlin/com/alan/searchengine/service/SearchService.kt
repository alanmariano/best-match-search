package com.alan.searchengine.service

import com.alan.searchengine.dto.Restaurant
import com.alan.searchengine.dto.RestaurantSearchParameters
import com.alan.searchengine.utils.compareCuisine
import com.alan.searchengine.utils.isLesserOrEqualsThan
import com.alan.searchengine.utils.isSubStringOf
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SearchService {

    @Autowired
    private lateinit var restaurantService: RestaurantService

    fun findMatches(searchParameter: RestaurantSearchParameters) : List<Restaurant> {
        val restaurantsList: List<Restaurant> = restaurantService.listAll()
        val filteredRestaurants = restaurantsList.filter {
            isSubStringOf(it.name, searchParameter.name) &&
            compareCuisine(it.cuisine, searchParameter.cuisine) &&
            isLesserOrEqualsThan(it.rating, searchParameter.rating) &&
            isLesserOrEqualsThan(it.distance, searchParameter.distance) &&
            isLesserOrEqualsThan(it.price, searchParameter.price)
        }
        return filteredRestaurants;
    }

}