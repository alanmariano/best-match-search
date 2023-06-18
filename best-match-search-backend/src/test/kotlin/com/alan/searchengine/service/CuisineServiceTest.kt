package com.alan.searchengine.service

import com.alan.searchengine.dto.Cuisine
import com.alan.searchengine.generator.generateCuisineRecords
import com.alan.searchengine.generator.generateCuisines
import com.alan.searchengine.repository.CSVImportRepository
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CuisineServiceTest {

    val csvImportRepository: CSVImportRepository = mockk()
    val cuisineService: CuisineService = spyk(CuisineService(csvImportRepository))

    /**
     * TESTING getAll
     */

    @Test
    fun givenNoCuisines_whenGetAllCuisines_thenReturnEmptyCuisinesList() {

        val csvRecords: List<Map<String, String>> = listOf()

        coEvery { csvImportRepository.getAllData("classpath:csv/cuisines.csv") } returns csvRecords

        val cuisines: List<Cuisine> = cuisineService.getAll()

        verify(exactly = 1) { csvImportRepository.getAllData("classpath:csv/cuisines.csv") }
        assertTrue(cuisines.isEmpty())

    }

    @Test
    fun givenThereAreCuisines_whenGetAllCuisines_thenReturnCuisinesList() {

        val csvRecords: List<Map<String, String>> = generateCuisineRecords()

        val expectedCuisines: List<Cuisine> = generateCuisines()

        coEvery { csvImportRepository.getAllData("classpath:csv/cuisines.csv") } returns csvRecords

        val cuisines: List<Cuisine> = cuisineService.getAll()

        verify(exactly = 1) { csvImportRepository.getAllData("classpath:csv/cuisines.csv") }
        assertEquals(expectedCuisines, cuisines)

    }

    /**
     * Testing getNames
     */

    @Test
    fun givenACuisineWithoutName_whenGetNames_thenDontReturnNullValuesInList() {

        val cuisines: List<Cuisine> = listOf(
            Cuisine(1, "American"),
            Cuisine(2, null),
            Cuisine(3, "Thai")
        )

        val expectedCuisineNames: List<String> = listOf("American", "Thai")

        every { cuisineService.getAll() } returns cuisines

        val cuisineNames: List<String> = cuisineService.getNames()

        assertEquals(expectedCuisineNames, cuisineNames)

    }

    @Test
    fun givenACuisineWithoutName_whenGetCuisineMapById_thenDontReturnNullValuesInMap() {

        val cuisines: List<Cuisine> = listOf(
            Cuisine(1, "American"),
            Cuisine(2, null),
            Cuisine(3, "Thai")
        )

        val expectedCuisineMap: Map<Long, Cuisine> = mapOf(
            Pair(1, Cuisine(1, "American")),
            Pair(3, Cuisine(3, "Thai"))
        )

        every { cuisineService.getAll() } returns cuisines

        val cuisineMap: Map<Long, Cuisine> = cuisineService.getCuisineMapById()

        assertEquals(expectedCuisineMap, cuisineMap)

    }

}