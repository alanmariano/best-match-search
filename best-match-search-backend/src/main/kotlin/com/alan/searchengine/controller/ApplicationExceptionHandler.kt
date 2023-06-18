package com.alan.searchengine.controller

import com.alan.searchengine.dto.ErrorDetails
import com.alan.searchengine.exception.SearchParameterValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

const val SEARCH_PARAMETER_INVALID = "Search Parameters Invalid"

@RestControllerAdvice
class ApplicationExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(SearchParameterValidationException::class)])
    fun handleInvalidSearchParameter(ex: SearchParameterValidationException, request: WebRequest): ResponseEntity<ErrorDetails> {
        val errorDetails = ErrorDetails(SEARCH_PARAMETER_INVALID, ex.message)
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }

}