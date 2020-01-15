package com.sha.formvalidator.core.validator

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 *
 */
class NumericRangeValidator(
        private val min: Long,
        private val max: Long,
        errorMessage: String) : TextValidator(errorMessage) {

    override fun isValid(value: String): Boolean {
        return try {
            value.toLong() in min..max
        } catch (e: NumberFormatException) {
            false
        }

    }
}
