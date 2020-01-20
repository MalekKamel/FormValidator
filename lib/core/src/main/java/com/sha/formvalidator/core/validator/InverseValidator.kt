package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors

/**
 * It's a validator that applies the "NOT" logical operator to the validator it wraps.
 *
 */
class InverseValidator<V>(validator: Validator<V>) : AbsValidator<V>() {
    private var v: Validator<V> = validator
    override var errorGenerator: ErrorGeneratorInterface = v.errorGenerator
        set(value) {
            field = value
            v.errorGenerator = value
        }

    override fun validate(): Boolean = !v.isValid

    override var value: V? = null
        set(value) {
            field = value
            v.value = value
        }

}
