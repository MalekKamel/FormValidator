package com.sha.formvalidator.core.validator

/**
 * A validator that returns true only if the text is within the given range.
 *
 */
class LengthRangeValidator(errorMessage: String, private val min: Long, private val max: Long) : TextValidator(errorMessage) {

    override fun isValid(value: String): Boolean {
        val length = value.length
        return length in min..max
    }
}
