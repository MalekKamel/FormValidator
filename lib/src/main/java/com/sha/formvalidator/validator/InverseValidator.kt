package com.sha.formvalidator.validator

import android.widget.EditText

/**
 * It's a validator that applies the "NOT" logical operator to the validator it wraps.
 *
 */
class InverseValidator : Validator {
    private var v: Validator? = null

    constructor(validator: Validator) : super("") {
        this.v = validator
    }

    constructor(errorMessage: String, validator: Validator) : super(errorMessage) {
        this.v = validator
    }

    override fun isValid(et: EditText): Boolean {
        return !v!!.isValid(et)
    }

}
