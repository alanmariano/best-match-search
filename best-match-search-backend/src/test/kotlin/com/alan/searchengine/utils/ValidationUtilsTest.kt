package com.alan.searchengine.utils

import com.alan.searchengine.dto.RestaurantSearchParameters
import com.alan.searchengine.exception.SearchParameterValidationException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ValidationUtilsTest {

    @Test
    fun givenPriceIsGreaterThanValid_whenValidatingSearchParameters_thenThrowException() {

        val searchParameters = RestaurantSearchParameters(
            "deli",
            null,
            null,
            51.0,
            null
        )

        val exception = assertThrows(SearchParameterValidationException::class.java) {
            validateSearchParameters(searchParameters)
        }

        assertEquals(PRICE_PARAMETER_INVALID_ERROR_MESSAGE, exception.message)

    }

    @Test
    fun givenPriceIsLesserThanValid_whenValidatingSearchParameters_thenThrowException() {

        val searchParameters = RestaurantSearchParameters(
            "deli",
            null,
            null,
            9.0,
            null
        )

        val exception = assertThrows(SearchParameterValidationException::class.java) {
            validateSearchParameters(searchParameters)
        }

        assertEquals(PRICE_PARAMETER_INVALID_ERROR_MESSAGE, exception.message)

    }

    @Test
    fun givenRatingIsGreaterThanValid_whenValidatingSearchParameters_thenThrowException() {

        val searchParameters = RestaurantSearchParameters(
            "deli",
            6.0,
            null,
            25.0,
            null
        )

        val exception = assertThrows(SearchParameterValidationException::class.java) {
            validateSearchParameters(searchParameters)
        }

        assertEquals(RATING_PARAMETER_INVALID_ERROR_MESSAGE, exception.message)

    }

    @Test
    fun givenRatingIsLesserThanValid_whenValidatingSearchParameters_thenThrowException() {

        val searchParameters = RestaurantSearchParameters(
            "deli",
            0.0,
            null,
            25.0,
            null
        )

        val exception = assertThrows(SearchParameterValidationException::class.java) {
            validateSearchParameters(searchParameters)
        }

        assertEquals(RATING_PARAMETER_INVALID_ERROR_MESSAGE, exception.message)

    }

    @Test
    fun givenDistanceIsGreaterThanValid_whenValidatingSearchParameters_thenThrowException() {

        val searchParameters = RestaurantSearchParameters(
            "deli",
            3.0,
            11.0,
            25.0,
            null
        )

        val exception = assertThrows(SearchParameterValidationException::class.java) {
            validateSearchParameters(searchParameters)
        }

        assertEquals(DISTANCE_PARAMETER_INVALID_ERROR_MESSAGE, exception.message)

    }

    @Test
    fun givenDistanceIsLesserThanValid_whenValidatingSearchParameters_thenThrowException() {

        val searchParameters = RestaurantSearchParameters(
            "deli",
            3.0,
            0.0,
            25.0,
            null
        )

        val exception = assertThrows(SearchParameterValidationException::class.java) {
            validateSearchParameters(searchParameters)
        }

        assertEquals(DISTANCE_PARAMETER_INVALID_ERROR_MESSAGE, exception.message)

    }

    @Test
    fun givenAllNumericParametersAreNull_whenValidatingSearchParameters_thenDontThrowException() {

        val searchParameters = RestaurantSearchParameters(
            "deli",
            null,
            null,
            null,
            null
        )

        assertDoesNotThrow() {
            validateSearchParameters(searchParameters)
        }

    }

    @Test
    fun givenAllNumericParametersAreWithinMinimumLimit_whenValidatingSearchParameters_thenDontThrowException() {

        val searchParameters = RestaurantSearchParameters(
            "deli",
            1.0,
            1.0,
            10.0,
            null
        )

        assertDoesNotThrow() {
            validateSearchParameters(searchParameters)
        }

    }

    @Test
    fun givenAllNumericParametersAreWithinMaximumLimit_whenValidatingSearchParameters_thenDontThrowException() {

        val searchParameters = RestaurantSearchParameters(
            "deli",
            5.0,
            10.0,
            50.0,
            null
        )

        assertDoesNotThrow() {
            validateSearchParameters(searchParameters)
        }

    }

}