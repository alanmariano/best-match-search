package com.alan.searchengine.service

import com.alan.searchengine.dto.Cuisine
import com.alan.searchengine.dto.Restaurant
import com.alan.searchengine.dto.RestaurantSearchParameters
import com.alan.searchengine.generator.generateCuisinesMapById
import com.alan.searchengine.generator.generateRestaurantRecords
import com.alan.searchengine.generator.generateRestaurants
import com.alan.searchengine.repository.CSVImportRepository
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RestaurantServiceTest {

    val csvImportRepository: CSVImportRepository = mockk()
    val cuisineService: CuisineService = mockk()
    val restaurantService: RestaurantService = spyk(RestaurantService(csvImportRepository, cuisineService))

    /**
     * TESTING getAll
     */

    @Test
    fun givenThereAreNoRestaurants_whenGetAllRestaurants_thenReturnEmptyRestaurantsList() {

        coEvery { csvImportRepository.getAllData("classpath:csv/restaurants.csv") } returns listOf()
        coEvery { cuisineService.getCuisineMapById() } returns mapOf()

        val restaurants: List<Restaurant> = restaurantService.getAll()

        verify(exactly = 1) { csvImportRepository.getAllData("classpath:csv/restaurants.csv") }
        verify(exactly = 1) { cuisineService.getCuisineMapById() }
        assertTrue(restaurants.isEmpty())

    }

    @Test
    fun givenThereAreRestaurants_whenGetAllRestaurants_thenReturnRestaurantsList() {

        val csvRecords: List<Map<String, String>> = generateRestaurantRecords()

        val expectedRestaurants: List<Restaurant> = generateRestaurants()

        val cuisinesMapById: Map<Long, Cuisine> = generateCuisinesMapById()

        coEvery { csvImportRepository.getAllData("classpath:csv/restaurants.csv") } returns csvRecords
        coEvery { cuisineService.getCuisineMapById() } returns cuisinesMapById

        val restaurants: List<Restaurant> = restaurantService.getAll()

        verify(exactly = 1) { csvImportRepository.getAllData("classpath:csv/restaurants.csv") }
        verify(exactly = 1) { cuisineService.getCuisineMapById() }
        assertEquals(expectedRestaurants, restaurants)

    }

    /**
     * TESTING getName
     */

    @Test
    fun givenARestaurantWithoutName_whenGetNames_thenDontReturnNullValuesInList() {

        val restaurants: List<Restaurant> = listOf(
            Restaurant("Delicious", 5.0, 1.0, 10.0, null),
            Restaurant(null, 5.0, 1.0, 10.0, null),
            Restaurant("Awful", 1.0, 10.0, 50.0, null)
        )

        val expectedRestaurantNames: List<String> = listOf("Delicious", "Awful")

        every { restaurantService.getAll() } returns restaurants

        val restaurantNames: List<String> = restaurantService.getNames()

        assertEquals(expectedRestaurantNames, restaurantNames)

    }

    /**
     * TESTING getRestaurantMatches
     */

    @Test
    fun givenOnlyNameSearchParameter_whenSearchMatches_thenReturnValidMatches() {

        val searchParameters: RestaurantSearchParameters = RestaurantSearchParameters("CHOW", null, null, null, null)

        val baseRestaurants: List<Restaurant> = generateRestaurants()

        val expectedRestaurants: List<Restaurant> = listOf(
            Restaurant("Wish Chow", 3.0,1.0,40.0, Cuisine(1, "American")),
            Restaurant("Chowish",3.0,2.0,10.0, Cuisine(6, "Japanese")),
            Restaurant("Hut Chow", 3.0,2.0,10.0, Cuisine(3, "Thai"))
        )

        every { restaurantService.getAll() } returns baseRestaurants

        val matchedExceptionRestaurants: List<Restaurant> = restaurantService.getRestaurantMatches(searchParameters)

        assertEquals(expectedRestaurants, matchedExceptionRestaurants)

    }

    @Test
    fun givenNameAndRatingSearchParameter_whenSearchMatches_thenReturnValidMatches() {

        val searchParameters: RestaurantSearchParameters = RestaurantSearchParameters("deli", 2.0, null, null, null)

        val baseRestaurants: List<Restaurant> = generateRestaurants()

        val expectedRestaurants: List<Restaurant> = listOf(
            Restaurant("Deliciouszoid",3.0,2.0,30.0,Cuisine(4, "Italian")),
            Restaurant("Deliciouszen",2.0,2.0,30.0,Cuisine(5, "French")),
            Restaurant("Deliciousquipo",5.0,3.0,10.0,Cuisine(6, "Japanese")),
            Restaurant("Gusto Delicious",5.0,3.0,50.0,Cuisine(2, "Chinese")),
            Restaurant("Fed Delicious",3.0,9.0,35.0,Cuisine(4, "Italian"))
        )

        every { restaurantService.getAll() } returns baseRestaurants

        val matchedExceptionRestaurants: List<Restaurant> = restaurantService.getRestaurantMatches(searchParameters)

        assertEquals(expectedRestaurants, matchedExceptionRestaurants)

    }

    @Test
    fun givenNameAndRatingAndDistanceSearchParameter_whenSearchMatches_thenReturnValidMatches() {

        val searchParameters: RestaurantSearchParameters = RestaurantSearchParameters("deli", 2.0, 3.5, null, null)

        val baseRestaurants: List<Restaurant> = generateRestaurants()

        val expectedRestaurants: List<Restaurant> = listOf(
            Restaurant("Deliciouszoid",3.0,2.0,30.0,Cuisine(4, "Italian")),
            Restaurant("Deliciouszen",2.0,2.0,30.0,Cuisine(5, "French")),
            Restaurant("Deliciousquipo",5.0,3.0,10.0,Cuisine(6, "Japanese")),
            Restaurant("Gusto Delicious",5.0,3.0,50.0,Cuisine(2, "Chinese"))
        )

        every { restaurantService.getAll() } returns baseRestaurants

        val matchedExceptionRestaurants: List<Restaurant> = restaurantService.getRestaurantMatches(searchParameters)

        assertEquals(expectedRestaurants, matchedExceptionRestaurants)

    }

    @Test
    fun givenNameAndRatingAndDistanceAndPriceSearchParameter_whenSearchMatches_thenReturnValidMatches() {

        val searchParameters: RestaurantSearchParameters = RestaurantSearchParameters("deli", 2.0, 3.5, 35.0, null)

        val baseRestaurants: List<Restaurant> = generateRestaurants()

        val expectedRestaurants: List<Restaurant> = listOf(
            Restaurant("Deliciouszoid",3.0,2.0,30.0,Cuisine(4, "Italian")),
            Restaurant("Deliciouszen",2.0,2.0,30.0,Cuisine(5, "French")),
            Restaurant("Deliciousquipo",5.0,3.0,10.0,Cuisine(6, "Japanese"))
        )

        every { restaurantService.getAll() } returns baseRestaurants

        val matchedExceptionRestaurants: List<Restaurant> = restaurantService.getRestaurantMatches(searchParameters)

        assertEquals(expectedRestaurants, matchedExceptionRestaurants)

    }

    @Test
    fun givenNameAndRatingAndDistanceAndPriceAndCuisineSearchParameter_whenSearchMatches_thenReturnValidMatches() {

        val searchParameters: RestaurantSearchParameters = RestaurantSearchParameters("deli", 2.0, 3.5, 35.0, "fre")

        val baseRestaurants: List<Restaurant> = generateRestaurants()

        val expectedRestaurants: List<Restaurant> = listOf(
            Restaurant("Deliciouszen",2.0,2.0,30.0,Cuisine(5, "French")),
        )

        every { restaurantService.getAll() } returns baseRestaurants

        val matchedExceptionRestaurants: List<Restaurant> = restaurantService.getRestaurantMatches(searchParameters)

        assertEquals(expectedRestaurants, matchedExceptionRestaurants)

    }

    @Test
    fun givenNameAndRatingAndDistanceAndPriceAndCuisineSearchParameter_whenSearchMatches_thenReturnZeroMatches() {

        val searchParameters: RestaurantSearchParameters = RestaurantSearchParameters("deli", 2.0, 3.5, 35.0, "chi")

        val baseRestaurants: List<Restaurant> = generateRestaurants()

        val expectedRestaurants: List<Restaurant> = listOf()

        every { restaurantService.getAll() } returns baseRestaurants

        val matchedExceptionRestaurants: List<Restaurant> = restaurantService.getRestaurantMatches(searchParameters)

        assertEquals(expectedRestaurants, matchedExceptionRestaurants)

    }

}