package com.sha.formvalidator.core.validator.composite

import com.sha.formvalidator.core.validator.Validator

/**
 * The AND validator checks if all of the passed validators is returning true.<br></br>
 * Note: the message that will be shown is the one of the first failing validator
 *
 */
class AndValidator<V>: CompositeValidator<V> {
    override var errorMessage: String = ""

    constructor(vararg validators: Validator<V>): super(*validators)
    constructor(validators: List<Validator<V>>): super(validators)

    override fun validate(): Boolean {
       val anyFails = validators.firstOrNull { !it.isValid }
        anyFails?.let {
            // error message equals the first failing validator
            this.errorMessage = it.errorMessage
            return false
        }
        return true
    }
}

