package com.alan.searchengine.repository

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Repository

@Repository
class CSVImportRepository(
    private val resourceLoader: ResourceLoader
) {

    fun getAllData(filePath: String): List<Map<String, String>> {

        val bufferedReader = resourceLoader.getResource(filePath).inputStream.bufferedReader()
        val recordsAsMaps: List<Map<String, String>> = CSVParser(bufferedReader, CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withTrim()).records.map { it.toMap() }
        bufferedReader.close()

        return recordsAsMaps

    }

}