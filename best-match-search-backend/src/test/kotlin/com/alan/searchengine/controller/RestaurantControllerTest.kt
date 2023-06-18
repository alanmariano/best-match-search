package com.alan.searchengine.controller

import com.alan.searchengine.dto.RestaurantSearchParameters
import com.alan.searchengine.service.RestaurantService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(RestaurantController::class)
class RestaurantControllerTest(@Autowired private val mockMvc: MockMvc)
{

    @MockkBean
    lateinit var restaurantService: RestaurantService

    @Test
    fun givenThereAreRestaurants_whenGetRestaurantNames_thenReturnOk() {

        every { restaurantService.getNames() } returns listOf();

        mockMvc.perform(get("/api/restaurants/names"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))

    }

    @Test
    fun givenAQueryWithoutANameParameter_whenSearchRestaurant_thenReturnBadRequest() {

        mockMvc.perform(get("/api/restaurants/search"))
            .andExpect(status().isBadRequest)

    }

    @Test
    fun givenAQueryWithANameParameter_whenSearchRestaurant_thenReturnOk() {

        val parameters = RestaurantSearchParameters("deli", null,null, null,null)

        every { restaurantService.getRestaurantMatches(parameters) } returns listOf();

        mockMvc.perform(get("/api/restaurants/search?name=deli"))
            .andExpect(status().isOk)

    }

}