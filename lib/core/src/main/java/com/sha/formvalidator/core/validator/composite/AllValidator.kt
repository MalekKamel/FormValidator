package com.sha.formvalidator.core.validator.composite

import com.sha.formvalidator.core.validator.Validator

/**
 * The ALL validator checks if all of the passed validators is returning true.<br></br>
 * Note: the message that will be shown is the one of the first failing validator
 *
 */
class AllValidator<V>: CompositeValidator<V> {
    override var errorMessage: String = ""

    constructor(vararg validators: Validator<V>): super(*validators)
    constructor(validators: List<Validator<V>>): super(validators)

    override fun validate(): Boolean {
       val anyFails = validators.firstOrNull { !it.isValid }
        anyFails?.let {
            // show the first failing validator error message
            this.errorMessage = it.errorMessage
            return false
        }
        this.errorMessage = ""
        return true
    }

}

