package com.alan.searchengine.utils

import com.alan.searchengine.dto.Cuisine
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SearchFilterUtilsStringFilterTest {

    @Test
    fun givenStringToFilterIsNull_whenCheckIfContainsSubString_thenReturnFalse() {

        val valueToFilter: String? = null
        val filter: String = "deli"

        val isSubstring: Boolean = isSubStringOf(valueToFilter, filter)

        assertFalse(isSubstring)

    }

    @Test
    fun givenSubstringIsNull_whenCheckIfContainsSubString_thenReturnTrue() {

        val valueToFilter: String = "Deliciouszoid"
        val filter: String? = null

        val isSubstring: Boolean = isSubStringOf(valueToFilter, filter)

        assertTrue(isSubstring)

    }

    @Test
    fun givenBothIsNull_whenCheckIfContainsSubString_thenReturnFalse() {

        val valueToFilter: String? = null
        val filter: String? = null

        val isSubstring: Boolean = isSubStringOf(valueToFilter, filter)

        assertFalse(isSubstring)

    }

    @Test
    fun givenNoneIsNullAndDontContainsSubstring_whenCheckIfContainsSubString_thenReturnFalse() {

        val valueToFilter: String = "DELICIOUSABCDE"
        val filter: String = "dexic"

        val isSubstring: Boolean = isSubStringOf(valueToFilter, filter)

        assertFalse(isSubstring)

    }

    @Test
    fun givenNoneIsNullAndContainsSubstring_whenCheckIfContainsSubString_thenReturnTrue() {

        val valueToFilter: String = "DELICIOUSABCDE"
        val filter: String = "elic"

        val isSubstring: Boolean = isSubStringOf(valueToFilter, filter)

        assertTrue(isSubstring)

    }

    @Test
    fun givenCuisineIsNull_whenCheckIfContainsSubstring_thenReturnFalse() {

        val valueToFilter: Cuisine? = null
        val filter: String = "chi"

        val isSubstring: Boolean = compareCuisine(valueToFilter, filter)

        assertFalse(isSubstring)

    }

    @Test
    fun givenCuisineIsNotNullAndContainsSubstring_whenCheckIfContainsSubstring_thenReturnTrue() {

        val valueToFilter: Cuisine? = Cuisine(1, "Chinese")
        val filter: String = "chi"

        val isSubstring: Boolean = compareCuisine(valueToFilter, filter)

        assertTrue(isSubstring)

    }

}