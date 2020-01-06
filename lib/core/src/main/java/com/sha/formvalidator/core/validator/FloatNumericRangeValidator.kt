package com.sha.formvalidator.core.validator

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 *
 * @author Said Tahsin Dane <tasomaniac></tasomaniac>@gmail.com>
 */
class FloatNumericRangeValidator(errorMessage: String, private val floatMin: Double, private val floatMax: Double) : TextValidator(errorMessage) {
    override fun isValid(value: String): Boolean {
       return try { value.toDouble() in floatMin..floatMax }
        catch (e: NumberFormatException) { false }
    }
}
