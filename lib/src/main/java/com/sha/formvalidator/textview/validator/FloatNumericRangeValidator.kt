package com.sha.formvalidator.textview.validator

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 *
 * @author Said Tahsin Dane <tasomaniac></tasomaniac>@gmail.com>
 */
class FloatNumericRangeValidator(errorMessage: String, private val floatMin: Double, private val floatMax: Double) : TextValidator(errorMessage) {
    override fun isValid(text: String): Boolean {
       return try { text.toDouble() in floatMin..floatMax }
        catch (e: NumberFormatException) { false }
    }
}
