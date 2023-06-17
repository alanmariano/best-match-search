@file:JvmName("ComparisonUtils")
@file:JvmMultifileClass

package com.alan.searchengine.utils

import com.alan.searchengine.dto.Cuisine
import java.util.*

fun prepareStringForComparison(value: String): String = value.lowercase(Locale.getDefault()).trim()

fun isSubStringOf(value: String, filter: String?): Boolean {
    return filter?.let { prepareStringForComparison(value).contains(prepareStringForComparison(filter)) } ?: true
}

fun isGreaterOrEqualsThan(value: Number, filter: Number?): Boolean {
    return filter?.let { value.toDouble() >= it.toDouble() } ?: true
}

fun isLesserOrEqualsThan(value: Number, filter: Number?): Boolean {
    return filter?.let { value.toDouble() <= it.toDouble() } ?: true
}

fun compareCuisine(cuisine: Cuisine?, filter: String?): Boolean {
    return cuisine?.let { isSubStringOf(it.name, filter) } ?: false
}