package com.sha.formvalidator.core.validator.composite

import com.sha.formvalidator.core.validator.Validator

/**
 * Abstract class for a multi-validator.
 *
 * @see AndValidator
 *
 * @see OrValidator
 */
abstract class CompositeValidator<V> : Validator<V> {
    var validators: MutableList<Validator<V>> = mutableListOf()

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
