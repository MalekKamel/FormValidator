package com.sha.formvalidator.textview.validator

import android.widget.TextView

/**
 * It's a validator that applies the "NOT" logical operator to the validator it wraps.
 *
 */
class InverseValidator : Validator {
    private var v: Validator

    constructor(validator: Validator) : super("") { this.v = validator }
    constructor(errorMessage: String, validator: Validator) : super(errorMessage) { this.v = validator }

    override fun isValid(tv: TextView): Boolean = !v.isValid(tv)

}
