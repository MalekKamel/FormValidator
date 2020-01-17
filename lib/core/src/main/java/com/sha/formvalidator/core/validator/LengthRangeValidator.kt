package com.sha.formvalidator.core.validator

/**
 * A validator that returns true only if the text is within the given range.
 *
 */
class LengthRangeValidator(errorMessage: String, private val min: Long, private val max: Long) : TextValidator(errorMessage) {

    override fun validate(): Boolean {
        return value.length in min..max
    }
}
