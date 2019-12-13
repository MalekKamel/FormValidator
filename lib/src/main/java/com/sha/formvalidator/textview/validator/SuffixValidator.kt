package com.sha.formvalidator.textview.validator

import android.widget.EditText

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class SuffixValidator(private val suffix: String, errorMessage: String) : Validator(errorMessage) {

    override fun isValid(et: EditText): Boolean {
        return et.text.toString().endsWith(suffix)
    }
}
