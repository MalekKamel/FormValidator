package com.sha.formvalidator.core.validator

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 **/
class FloatRangeValidator(
        private val floatMin: Float,
        private val floatMax: Float,
        errorMessage: String
) : TextValidator(errorMessage) {
    override fun validate(): Boolean {
       return try { value.toDouble() in floatMin..floatMax } catch (e: NumberFormatException) { false }
    }
}
