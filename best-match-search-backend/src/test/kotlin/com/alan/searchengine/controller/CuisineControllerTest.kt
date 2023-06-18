package com.alan.searchengine.controller

import com.alan.searchengine.service.CuisineService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(CuisineController::class)
class CuisineControllerTest(@Autowired private val mockMvc: MockMvc) {

    @MockkBean
    lateinit var cuisineService: CuisineService

    @Test
    fun givenThereAreCuisines_whenGetCuisineNames_thenReturnOk() {

        every { cuisineService.getNames() } returns listOf();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cuisines/names"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

    }

}