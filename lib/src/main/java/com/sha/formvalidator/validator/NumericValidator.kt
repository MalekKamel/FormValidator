package com.sha.formvalidator.validator

import android.text.TextUtils
import android.widget.EditText

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class NumericValidator(errorMessage: String) : Validator(errorMessage) {

    override fun isValid(et: EditText): Boolean {
        return TextUtils.isDigitsOnly(et.text)
    }
}
