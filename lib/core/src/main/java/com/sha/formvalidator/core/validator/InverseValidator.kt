package com.sha.formvalidator.core.validator

/**
 * It's a validator that applies the "NOT" logical operator to the validator it wraps.
 *
 */
class InverseValidator(validator: TextValidator, errorMessage: String = "") : TextValidator(errorMessage) {
    private var v: TextValidator = validator

    override fun isValid(value: String): Boolean = !v.isValid(value)

}
