package com.alan.searchengine.service

import com.alan.searchengine.dto.Cuisine
import com.alan.searchengine.dto.Restaurant
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service

@Service
class CSVImport {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    fun importRestaurants(): List<Restaurant> {

        val cuisinesByID: Map<Long, Cuisine> = this.importCuisines()

        val bufferedReader = resourceLoader.getResource("classpath:csv/restaurants.csv").inputStream.bufferedReader()

        val restaurants: List<Restaurant> = CSVParser(bufferedReader, CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withTrim()).records.map {
                Restaurant(
                    it["name"],
                    it["customer_rating"].toDouble(),
                    it["distance"].toDouble(),
                    it["price"].toDouble(),
                    cuisinesByID[it["cuisine_id"].toLong()]
                )
            }

        return restaurants;

    }

    private fun importCuisines(): Map<Long, Cuisine> {

        val bufferedReader = resourceLoader.getResource("classpath:csv/cuisines.csv").inputStream.bufferedReader()

        val cuisinesByID: Map<Long, Cuisine> = CSVParser(bufferedReader, CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withTrim()).records.associate {
                it["id"].toLong() to Cuisine(
                    it["id"].toLong(),
                    it["name"]
                )
            }

        return cuisinesByID;

    }


}