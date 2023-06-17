package com.alan.searchengine.repository

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Repository

@Repository
class CSVImportRepository(
    private val resourceLoader: ResourceLoader
) {

    fun getAllData(filePath: String): List<CSVRecord> {

        val bufferedReader = resourceLoader.getResource(filePath).inputStream.bufferedReader()

        return CSVParser(bufferedReader, CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withTrim()).records

    }

}