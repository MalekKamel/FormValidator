package com.sha.formvalidator.core.validator.composite

import com.sha.formvalidator.core.validator.TextValidator

/**
 * Abstract class for a multi-validator.
 *
 * @see AndValidator
 *
 * @see OrValidator
 */
abstract class CompositeValidator : TextValidator {
    protected var validators: MutableList<TextValidator> = mutableListOf()

    constructor(message: String, vararg validators: TextValidator): super(message) {
        this.validators = validators.toMutableList()
    }

    constructor(message: String, validators: List<TextValidator>): super(message) {
        this.validators = validators.toMutableList()
    }

    fun enqueue(newValidator: TextValidator) {
        validators.add(newValidator)
    }
}
