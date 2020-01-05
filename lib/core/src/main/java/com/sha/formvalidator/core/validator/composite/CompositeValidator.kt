package com.sha.formvalidator.core.validator.composite

import com.sha.formvalidator.core.validator.TextValidator

/**
 * Abstract class for a multi-validator.
 *
 * @see AndValidator
 *
 * @see OrValidator
 */
abstract class CompositeValidator(message: String, vararg validators: TextValidator) : TextValidator(message) {
    protected val validators: MutableList<TextValidator> = mutableListOf(*validators)

    fun enqueue(newValidator: TextValidator) {
        validators.add(newValidator)
    }
}
