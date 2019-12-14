package com.sha.formvalidator.textview.validator

import android.text.TextUtils
import android.widget.TextView

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class NumericValidator(errorMessage: String) : TextViewValidator(errorMessage) {

    override fun isValid(tv: TextView): Boolean {
        return TextUtils.isDigitsOnly(tv.text)
    }
}
