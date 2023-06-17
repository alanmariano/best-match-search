package com.alan.searchengine.service

interface ServiceInterface<T> {

    fun getAll(): List<T>
    fun getNames(): List<String>

}