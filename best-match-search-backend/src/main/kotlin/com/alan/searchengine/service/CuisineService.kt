package com.alan.searchengine.service

import com.alan.searchengine.dto.Cuisine
import com.alan.searchengine.repository.CSVImportRepository
import org.springframework.stereotype.Service

@Service
class CuisineService(
    private val csvImportRepository: CSVImportRepository
) : ServiceInterface<Cuisine> {

    override fun getAll(): List<Cuisine> {
        val recordsAsMaps: List<Map<String, String>> = csvImportRepository.getAllData("classpath:csv/cuisines.csv")

        val cuisines: List<Cuisine> = recordsAsMaps.map {
            Cuisine(
                it["id"]?.toLong(),
                it["name"]
            )
        }

        return cuisines;

    }

    override fun getNames(): List<String> = this.getAll().mapNotNull { it.name }

    fun getCuisineMapById(): Map<Long, Cuisine> {

        val cuisines : List<Cuisine> = this.getAll()

        val cuisinesByID: Map<Long, Cuisine> = cuisines
            .mapNotNull { c -> c.id?.let { c.name?.let { Pair(c.id, c) } } }
            .toMap()

        return cuisinesByID;

    }

}