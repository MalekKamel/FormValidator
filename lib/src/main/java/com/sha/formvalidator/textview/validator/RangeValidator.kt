package com.sha.formvalidator.textview.validator

import android.widget.EditText

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 *
 */
class RangeValidator(errorMessage: String, private val min: Int, private val max: Int) : Validator(errorMessage) {

    override fun isValid(et: EditText): Boolean {
        val length = et.text.toString().length
        return length >= min && length <= max
    }
}
