package com.alan.searchengine.utils

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SearchFilterUtilsNumberFilterTest {

    /**
     * TESTS ON isGreaterOrEquals method
     */

    @Test
    fun givenNumberToFilterIsNull_whenCheckIfIsGreaterOrEqual_thenReturnFalse() {

        val numberToFilter: Number? = null
        val filter: Number = 10

        val isGreaterOrEqual: Boolean = isGreaterOrEqualsThan(numberToFilter, filter)

        assertFalse(isGreaterOrEqual)

    }

    @Test
    fun givenFilterIsNull_whenCheckIfIsGreaterOrEqual_thenReturnTrue() {

        val numberToFilter: Number? = 10
        val filter: Number? = null

        val isGreaterOrEqual: Boolean = isGreaterOrEqualsThan(numberToFilter, filter)

        assertTrue(isGreaterOrEqual)

    }

    @Test
    fun givenBothIsNull_whenCheckIfIsGreaterOrEqual_thenReturnFalse() {

        val numberToFilter: Number? = null
        val filter: Number? = null

        val isGreaterOrEqual: Boolean = isGreaterOrEqualsThan(numberToFilter, filter)

        assertFalse(isGreaterOrEqual)

    }

    @Test
    fun givenNoneIsNullAndIsNotGreater_whenCheckIfIsGreaterOrEqual_thenReturnFalse() {

        val numberToFilter: Number = 5
        val filter: Number = 6

        val isGreaterOrEqual: Boolean = isGreaterOrEqualsThan(numberToFilter, filter)

        assertFalse(isGreaterOrEqual)

    }

    @Test
    fun givenNoneIsNullAndIsGreater_whenCheckIfIsGreaterOrEqual_thenReturnTrue() {

        val numberToFilter: Number = 7
        val filter: Number = 6

        val isGreaterOrEqual: Boolean = isGreaterOrEqualsThan(numberToFilter, filter)

        assertTrue(isGreaterOrEqual)

    }

    /**
     * TESTS ON isLesserOrEquals method
     */

    @Test
    fun givenNumberToFilterIsNull_whenCheckIfIsLesserOrEqual_thenReturnFalse() {

        val numberToFilter: Number? = null
        val filter: Number = 10

        val isLesserOrEqual: Boolean = isLesserOrEqualsThan(numberToFilter, filter)

        assertFalse(isLesserOrEqual)

    }

    @Test
    fun givenFilterIsNull_whenCheckIfIsLesserOrEqual_thenReturnTrue() {

        val numberToFilter: Number? = 10
        val filter: Number? = null

        val isLesserOrEqual: Boolean = isLesserOrEqualsThan(numberToFilter, filter)

        assertTrue(isLesserOrEqual)

    }

    @Test
    fun givenBothIsNull_whenCheckIfIsLesserOrEqual_thenReturnFalse() {

        val numberToFilter: Number? = null
        val filter: Number? = null

        val isLesserOrEqual: Boolean = isLesserOrEqualsThan(numberToFilter, filter)

        assertFalse(isLesserOrEqual)

    }

    @Test
    fun givenNoneIsNullAndIsNotLesser_whenCheckIfIsLesserOrEqual_thenReturnFalse() {

        val numberToFilter: Number = 6
        val filter: Number = 5

        val isLesserOrEqual: Boolean = isLesserOrEqualsThan(numberToFilter, filter)

        assertFalse(isLesserOrEqual)

    }

    @Test
    fun givenNoneIsNullAndIsLesser_whenCheckIfIsLesserOrEqual_thenReturnTrue() {

        val numberToFilter: Number = 6
        val filter: Number = 7

        val isLesserOrEqual: Boolean = isLesserOrEqualsThan(numberToFilter, filter)

        assertTrue(isLesserOrEqual)

    }


}