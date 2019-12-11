package com.sha.formvalidator.validator

import android.widget.EditText

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class PrefixValidator(private val prefix: String, errorMessage: String) : Validator(errorMessage) {

    override fun isValid(et: EditText): Boolean {
        return et.text.toString().startsWith(prefix)
    }
}
