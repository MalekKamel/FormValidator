package com.sha.formvalidator.core.validator

/**
 * It's a validator that applies the "NOT" logical operator to the validator it wraps.
 *
 */
class InverseValidator<V>(validator: Validator<V>) : Validator<V> {
    private var v: Validator<V> = validator
    override var errorMessage: String = v.errorMessage
        set(value) {
            field = value
            v.errorMessage = value
        }

    override fun validate(): Boolean = !v.isValid

    override var value: V? = null
        set(value) {
            field = value
            v.value = value
        }

}
