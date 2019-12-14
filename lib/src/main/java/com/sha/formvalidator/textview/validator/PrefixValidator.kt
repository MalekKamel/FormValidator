package com.sha.formvalidator.textview.validator

import android.widget.TextView

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class PrefixValidator(private val prefix: String, errorMessage: String) : TextViewValidator(errorMessage) {

    override fun isValid(tv: TextView): Boolean = tv.text.toString().startsWith(prefix)
}
