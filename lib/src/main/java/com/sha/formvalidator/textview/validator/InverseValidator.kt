package com.sha.formvalidator.textview.validator

import android.widget.TextView

/**
 * It's a validator that applies the "NOT" logical operator to the validator it wraps.
 *
 */
class InverseValidator(validator: TextViewValidator, errorMessage: String = "") : TextViewValidator(errorMessage) {
    private var v: TextViewValidator = validator

    override fun isValid(tv: TextView): Boolean = !v.isValid(tv)

}
