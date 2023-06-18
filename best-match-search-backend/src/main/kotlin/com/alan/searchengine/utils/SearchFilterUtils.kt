@file:JvmName("SearchFilterUtils")
@file:JvmMultifileClass

package com.alan.searchengine.utils

import com.alan.searchengine.dto.Cuisine
import java.util.*

private fun prepareStringForComparison(value: String): String = value.lowercase(Locale.getDefault()).trim()

fun isSubStringOf(valueToFilter: String?, filter: String?): Boolean {

    return when {
        valueToFilter == null -> {
            false
        }
        filter != null -> {
            prepareStringForComparison(valueToFilter).contains(prepareStringForComparison(filter))
        } else -> {
            true
        }
    }

}

fun isGreaterOrEqualsThan(valueToFilter: Number?, filter: Number?): Boolean {

    return when {
        valueToFilter == null -> {
            false
        }
        filter != null -> {
            valueToFilter.toDouble() >= filter.toDouble()
        } else -> {
            true
        }
    }

}

fun isLesserOrEqualsThan(valueToFilter: Number?, filter: Number?): Boolean {

    return when {
        valueToFilter == null -> {
            false
        }
        filter != null -> {
            valueToFilter.toDouble() <= filter.toDouble()
        } else -> {
            true
        }
    }

}

fun compareCuisine(cuisineToFilter: Cuisine?, filter: String?): Boolean {
    return cuisineToFilter?.let { isSubStringOf(it.name, filter) } ?: false
}