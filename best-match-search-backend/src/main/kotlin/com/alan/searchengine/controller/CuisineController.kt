package com.alan.searchengine.controller

import com.alan.searchengine.dto.Cuisine
import com.alan.searchengine.service.CuisineService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cuisines")
class CuisineController(
    private val cuisineService: CuisineService
) : ControllerInterface<Cuisine>(cuisineService)