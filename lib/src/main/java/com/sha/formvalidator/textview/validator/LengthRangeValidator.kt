package com.sha.formvalidator.textview.validator

/**
 * A validator that returns true only if the text is within the given range.
 *
 */
class LengthRangeValidator(errorMessage: String, private val min: Int, private val max: Int) : TextValidator(errorMessage) {

    override fun isValid(text: String): Boolean {
        val length = text.length
        return length in min..max
    }
}
