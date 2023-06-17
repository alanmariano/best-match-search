package com.alan.searchengine.controller

import com.alan.searchengine.service.ServiceInterface
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
abstract class ControllerInterface<T>(
    private val service: ServiceInterface<T>
) {

    @GetMapping("/names")
    fun getNames(): List<String> = this.service.getNames()

}