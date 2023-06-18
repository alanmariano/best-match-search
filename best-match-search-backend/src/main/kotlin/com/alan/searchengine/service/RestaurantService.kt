package com.alan.searchengine.service

import com.alan.searchengine.dto.Cuisine
import com.alan.searchengine.dto.Restaurant
import com.alan.searchengine.dto.RestaurantSearchParameters
import com.alan.searchengine.repository.CSVImportRepository
import com.alan.searchengine.utils.*
import org.springframework.stereotype.Service

@Service
class RestaurantService(
    private val csvImportRepository: CSVImportRepository,
    private val cuisineService: CuisineService
) : ServiceInterface<Restaurant> {

    override fun getAll(): List<Restaurant> {

        val recordsAsMaps: List<Map<String, String>> = csvImportRepository.getAllData("classpath:csv/restaurants.csv")
        val cuisineMapById: Map<Long, Cuisine> = cuisineService.getCuisineMapById()

        val restaurants: List<Restaurant> = recordsAsMaps.map {
            Restaurant(
                it["name"],
                it["customer_rating"]?.toDouble(),
                it["distance"]?.toDouble(),
                it["price"]?.toDouble(),
                it["cuisine_id"]?.let { id -> cuisineMapById[id.toLong()] }
            )
        }

        return restaurants;

    }

    override fun getNames(): List<String> = this.getAll().mapNotNull { it.name }

    private fun filterRestaurants(searchParameters: RestaurantSearchParameters): List<Restaurant> {

        val restaurantsList: List<Restaurant> = this.getAll()

        val filteredRestaurants = restaurantsList.filter {
            isSubStringOf(it.name, searchParameters.name) &&
                    compareCuisine(it.cuisine, searchParameters.cuisine) &&
                    isGreaterOrEqualsThan(it.rating, searchParameters.rating) &&
                    isLesserOrEqualsThan(it.distance, searchParameters.distance) &&
                    isLesserOrEqualsThan(it.price, searchParameters.price)
        }

        return filteredRestaurants;

    }

    private fun sortMatches(matches: List<Restaurant>): List<Restaurant> {

        if(matches.isEmpty()) {
            return matches
        }

        val distanceSortAscending: Comparator<Restaurant> = compareBy { it.distance }
        val ratingSortDescending: Comparator<Restaurant> = compareByDescending { it.rating }
        val priceSortAscending: Comparator<Restaurant> = compareBy { it.price }
        val cuisineSortAscending: Comparator<Restaurant> = compareBy{ it.cuisine?.name }
        val nameSortAscending: Comparator<Restaurant> = compareBy { it.name }

        return matches.sortedWith (
            distanceSortAscending.then(ratingSortDescending).then(priceSortAscending).then(cuisineSortAscending).then(nameSortAscending)
        )

    }

    fun getRestaurantMatches(searchParameters: RestaurantSearchParameters, maximumResultSize: Int = 5) : List<Restaurant> {

        validateSearchParameters(searchParameters)

        val filteredRestaurants: List<Restaurant> = this.filterRestaurants(searchParameters)
        val sortedRestaurant: List<Restaurant> = this.sortMatches(filteredRestaurants)

        return if(sortedRestaurant.isNotEmpty() && sortedRestaurant.size >= maximumResultSize)
            sortedRestaurant.subList(0, maximumResultSize)
        else
            sortedRestaurant

    }

}