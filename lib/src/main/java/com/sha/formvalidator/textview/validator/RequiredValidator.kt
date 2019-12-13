package com.sha.formvalidator.textview.validator

import android.widget.TextView

/**
 * A simple validator that validates the field only if the field is not empty.
 *
 */
class RequiredValidator : Validator {

    constructor() : super("") {}

    constructor(errorMessage: String) : super(errorMessage) {}

    override fun isValid(tv: TextView): Boolean {
        return tv.text.toString().trim { it <= ' ' }.isNotEmpty()
    }
}
