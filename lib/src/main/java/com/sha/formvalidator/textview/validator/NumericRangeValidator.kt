package com.sha.formvalidator.textview.validator

import android.widget.TextView

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 *
 */
class NumericRangeValidator(errorMessage: String, private val min: Long, private val max: Long) : Validator(errorMessage) {

    override fun isValid(tv: TextView): Boolean {
        return try {
            val value = java.lang.Long.parseLong(tv.text.toString())
            value in min..max
        } catch (e: NumberFormatException) {
            false
        }

    }
}
