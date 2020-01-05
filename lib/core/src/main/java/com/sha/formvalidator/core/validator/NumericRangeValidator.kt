package com.sha.formvalidator.core.validator

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 *
 */
class NumericRangeValidator(errorMessage: String, private val min: Long, private val max: Long) : TextValidator(errorMessage) {

    override fun isValid(text: String): Boolean {
        return try {
            val value = java.lang.Long.parseLong(text)
            value in min..max
        } catch (e: NumberFormatException) {
            false
        }

    }
}
