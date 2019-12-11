package com.sha.formvalidator.validator

import android.widget.EditText

/**
 * A simple validator that validates the field only if the field is not empty.
 *
 */
class RequiredValidator : Validator {

    constructor() : super("") {}

    constructor(errorMessage: String) : super(errorMessage) {}

    override fun isValid(et: EditText): Boolean {
        return et.text.toString().trim { it <= ' ' }.isNotEmpty()
    }
}
