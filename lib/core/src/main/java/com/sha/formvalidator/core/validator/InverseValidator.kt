package com.sha.formvalidator.core.validator

/**
 * It's a validator that applies the "NOT" logical operator to the validator it wraps.
 *
 */
class InverseValidator(validator: TextValidator, errorMessage: String = "") : TextValidator(errorMessage) {
    private var v: TextValidator = validator

    override fun validate(): Boolean = !v.isValid

    override var value: String = ""
        set(value) {
            field = value
            v.value = value
        }

}
