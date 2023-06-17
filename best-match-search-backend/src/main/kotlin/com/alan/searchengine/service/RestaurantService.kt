package com.alan.searchengine.service

import com.alan.searchengine.dto.Restaurant
import org.springframework.beans.factory.annotation.Autowired

class RestaurantService {

    @Autowired
    private lateinit var importService: CSVImportService

    fun listAll(): List<Restaurant>  = importService.importRestaurants()

}