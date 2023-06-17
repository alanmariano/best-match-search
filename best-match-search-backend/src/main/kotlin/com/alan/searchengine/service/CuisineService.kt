package com.alan.searchengine.service

import com.alan.searchengine.dto.Cuisine
import com.alan.searchengine.repository.CSVImportRepository
import org.apache.commons.csv.CSVRecord
import org.springframework.stereotype.Service

@Service
class CuisineService (
    private val csvImportRepository: CSVImportRepository
) {

    fun getAll() : List<Cuisine> {

        val csvDataList : List<CSVRecord> = csvImportRepository.getAllData("classpath:csv/cuisines.csv")

        val cuisines : List<Cuisine> = csvDataList.map {
                Cuisine(
                    it["id"].toLong(),
                    it["name"]
                )
            }

        return cuisines

    }

    fun getCuisineNames() : List<String> = this.getAll().map { it.name }

    fun getCuisineMapById() : Map<Long, Cuisine> {

        val cuisines : List<Cuisine> = this.getAll()

        val cuisinesByID : Map<Long, Cuisine> = cuisines.associate {
            it.id to Cuisine(
                it.id,
                it.name
            )
        }

        return cuisinesByID;

    }

}