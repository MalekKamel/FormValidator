package com.sha.formvalidator.validator

import android.widget.EditText

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 *
 * @author Said Tahsin Dane <tasomaniac></tasomaniac>@gmail.com>
 */
class FloatNumericRangeValidator(errorMessage: String, private val floatMin: Double, private val floatMax: Double) : Validator(errorMessage) {

    override fun isValid(et: EditText): Boolean {
        try {
            val value = java.lang.Double.parseDouble(et.text.toString())
            return value >= floatMin && value <= floatMax
        } catch (e: NumberFormatException) {
            return false
        }

    }
}
