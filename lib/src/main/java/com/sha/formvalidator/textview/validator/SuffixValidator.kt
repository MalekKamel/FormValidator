package com.sha.formvalidator.textview.validator

import android.widget.TextView

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class SuffixValidator(private val suffix: String, errorMessage: String) : Validator(errorMessage) {

    override fun isValid(tv: TextView): Boolean {
        return tv.text.toString().endsWith(suffix)
    }
}
