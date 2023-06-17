@file:JvmName("ValidationUtils")
@file:JvmMultifileClass

package com.alan.searchengine.utils

import com.alan.searchengine.dto.RestaurantSearchParameters
import com.alan.searchengine.exception.SearchParameterValidationException

fun validateSearchParameters(searchParameters: RestaurantSearchParameters) {

    if(isNotPriceParameterValid(searchParameters.price)) {
        throw SearchParameterValidationException("The Price Parameter value should be between 10 and 50")
    }

    if(isNotRatingParameterValid(searchParameters.rating)) {
        throw SearchParameterValidationException("The Rating Parameter value should be between 1 and 5")
    }

    if(isNotDistanceParameterValid(searchParameters.distance)) {
        throw SearchParameterValidationException("The Distance Parameter value should be between 1 and 10")
    }

}

private fun isNotPriceParameterValid(price: Double?): Boolean = price != null && (price < 10.0 || price > 50)

private fun isNotRatingParameterValid(rating: Double?): Boolean = rating != null && (rating < 1 || rating > 5)

private fun isNotDistanceParameterValid(distance: Double?): Boolean = distance != null && (distance < 1 || distance > 10)