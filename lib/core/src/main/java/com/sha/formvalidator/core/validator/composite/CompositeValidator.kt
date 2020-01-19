package com.sha.formvalidator.core.validator.composite

import com.sha.formvalidator.core.validator.AbsValidator
import com.sha.formvalidator.core.validator.Validator

/**
 * Abstract class for a multi-validator.
 *
 * @see AllValidator
 *
 * @see AnyValidator
 */
abstract class CompositeValidator<V> : AbsValidator<V> {
    var validators: MutableList<Validator<V>> = mutableListOf()
    var isSingleField = false

    constructor(vararg validators: Validator<V>): super() {
        this.validators = validators.toMutableList()
    }

    constructor(validators: List<Validator<V>>): super() {
        this.validators = validators.toMutableList()
    }

    fun enqueue(validator: Validator<V>) {
        validators.add(validator)
    }

    operator fun plus(validator: Validator<V>) {
        enqueue(validator)
    }

    override var value: V? = null
        set(value) {
            field = value
            validators.forEach { it.value = value }
        }
}
