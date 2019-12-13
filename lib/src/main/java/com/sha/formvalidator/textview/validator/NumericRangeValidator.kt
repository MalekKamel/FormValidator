package com.sha.formvalidator.textview.validator

import android.widget.EditText

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 *
 */
class NumericRangeValidator(errorMessage: String, private val min: Long, private val max: Long) : Validator(errorMessage) {

    override fun isValid(et: EditText): Boolean {
        try {
            val value = java.lang.Long.parseLong(et.text.toString())
            return value >= min && value <= max
        } catch (e: NumberFormatException) {
            return false
        }

    }
}
