@file:JvmName("ValidationUtils")
@file:JvmMultifileClass

package com.alan.searchengine.utils

import com.alan.searchengine.dto.RestaurantSearchParameters
import com.alan.searchengine.exception.SearchParameterValidationException

const val PRICE_MIN_ALLOWED_VALUE = 10.0
const val PRICE_MAX_ALLOWED_VALUE = 50.0

const val RATING_MIN_ALLOWED_VALUE = 1
const val RATING_MAX_ALLOWED_VALUE = 5

const val DISTANCE_MIN_ALLOWED_VALUE = 1
const val DISTANCE_MAX_ALLOWED_VALUE = 10

const val PRICE_PARAMETER_INVALID_ERROR_MESSAGE = "The Price Parameter value should be between $PRICE_MIN_ALLOWED_VALUE and $PRICE_MAX_ALLOWED_VALUE"
const val RATING_PARAMETER_INVALID_ERROR_MESSAGE = "The Rating Parameter value should be between $RATING_MIN_ALLOWED_VALUE and $RATING_MAX_ALLOWED_VALUE"
const val DISTANCE_PARAMETER_INVALID_ERROR_MESSAGE = "The Distance Parameter value should be between $DISTANCE_MIN_ALLOWED_VALUE and $DISTANCE_MAX_ALLOWED_VALUE"

fun validateSearchParameters(searchParameters: RestaurantSearchParameters) {

    if(isNotPriceParameterValid(searchParameters.price)) {
        throw SearchParameterValidationException(PRICE_PARAMETER_INVALID_ERROR_MESSAGE)
    }

    if(isNotRatingParameterValid(searchParameters.rating)) {
        throw SearchParameterValidationException(RATING_PARAMETER_INVALID_ERROR_MESSAGE)
    }

    if(isNotDistanceParameterValid(searchParameters.distance)) {
        throw SearchParameterValidationException(DISTANCE_PARAMETER_INVALID_ERROR_MESSAGE)
    }

}

private fun isNotPriceParameterValid(price: Double?): Boolean = price != null && (price < PRICE_MIN_ALLOWED_VALUE || price > PRICE_MAX_ALLOWED_VALUE)

private fun isNotRatingParameterValid(rating: Double?): Boolean = rating != null && (rating < RATING_MIN_ALLOWED_VALUE || rating > RATING_MAX_ALLOWED_VALUE)

private fun isNotDistanceParameterValid(distance: Double?): Boolean = distance != null && (distance < DISTANCE_MIN_ALLOWED_VALUE || distance > DISTANCE_MAX_ALLOWED_VALUE)