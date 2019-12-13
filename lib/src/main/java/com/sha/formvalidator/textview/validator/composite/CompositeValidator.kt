package com.sha.formvalidator.textview.validator.composite

import com.sha.formvalidator.textview.validator.Validator
import java.util.*

/**
 * Abstract class for a multivalidator.
 *
 * @see AndValidator
 *
 * @see OrValidator
 */
abstract class CompositeValidator : Validator {
    protected val validators: MutableList<Validator>

    constructor(message: String, vararg validators: Validator) : super(message) {
        if (validators == null) throw NullPointerException("validators are null")
        this.validators = ArrayList(Arrays.asList(*validators))
    }

    constructor(message: String) : super(message) {
        this.validators = ArrayList()
    }

    fun enqueue(newValidator: Validator) {
        validators.add(newValidator)
    }


}
