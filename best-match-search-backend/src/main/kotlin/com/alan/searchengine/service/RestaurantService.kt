package com.alan.searchengine.service

import com.alan.searchengine.dto.Cuisine
import com.alan.searchengine.dto.Restaurant
import com.alan.searchengine.dto.RestaurantSearchParameters
import com.alan.searchengine.repository.CSVImportRepository
import com.alan.searchengine.utils.compareCuisine
import com.alan.searchengine.utils.isGreaterOrEqualsThan
import com.alan.searchengine.utils.isLesserOrEqualsThan
import com.alan.searchengine.utils.isSubStringOf
import org.apache.commons.csv.CSVRecord
import org.springframework.stereotype.Service

@Service
class RestaurantService (
    private val csvImportRepository: CSVImportRepository,
    private val cuisineService: CuisineService
) {

    fun getAll(): List<Restaurant> {

        val csvDataList : List<CSVRecord> = csvImportRepository.getAllData("classpath:csv/restaurants.csv")
        val cuisineMapById : Map<Long, Cuisine> = cuisineService.getCuisineMapById()

        val restaurants: List<Restaurant> = csvDataList.map {
            Restaurant(
                it["name"],
                it["customer_rating"].toDouble(),
                it["distance"].toDouble(),
                it["price"].toDouble(),
                cuisineMapById[it["cuisine_id"].toLong()]
            )
        }

        return restaurants;

    }

    fun getRestaurantNames() : List<String> = this.getAll().map { it.name }

    private fun filterRestaurants(searchParameter: RestaurantSearchParameters) : List<Restaurant> {

        val restaurantsList: List<Restaurant> = this.getAll()

        val filteredRestaurants = restaurantsList.filter {
            isSubStringOf(it.name, searchParameter.name) &&
                    compareCuisine(it.cuisine, searchParameter.cuisine) &&
                    isGreaterOrEqualsThan(it.rating, searchParameter.rating) &&
                    isLesserOrEqualsThan(it.distance, searchParameter.distance) &&
                    isLesserOrEqualsThan(it.price, searchParameter.price)
        }

        return filteredRestaurants;

    }

    private fun sortMatches(matches: List<Restaurant>) : List<Restaurant> {

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

    fun getRestaurantMatches(searchParameter: RestaurantSearchParameters, maximumResultSize: Int = 5) : List<Restaurant> {

        val filteredRestaurants: List<Restaurant> = this.filterRestaurants(searchParameter)
        val sortedRestaurant: List<Restaurant> = this.sortMatches(filteredRestaurants)

        return if(sortedRestaurant.isNotEmpty() && sortedRestaurant.size >= maximumResultSize)
            sortedRestaurant.subList(0, maximumResultSize)
        else
            sortedRestaurant

    }

}