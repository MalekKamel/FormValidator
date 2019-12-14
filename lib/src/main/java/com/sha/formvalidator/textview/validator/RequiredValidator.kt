package com.sha.formvalidator.textview.validator

import android.widget.TextView

/**
 * A simple validator that validates the field only if the field is not empty.
 *
 */
class RequiredValidator(errorMessage: String = "") : TextViewValidator(errorMessage) {

    override fun isValid(tv: TextView): Boolean = tv.text.toString().trim { it <= ' ' }.isNotEmpty()
}
