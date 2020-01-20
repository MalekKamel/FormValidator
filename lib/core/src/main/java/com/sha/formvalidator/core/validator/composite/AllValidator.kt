package com.sha.formvalidator.core.validator.composite

import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.ErrorGeneratorInterface
import com.sha.formvalidator.core.validator.Validator

/**
 * The ALL validator checks if all of the passed validators is returning true.<br></br>
 * Note: the message that will be shown is the one of the first failing validator
 *
 */
class AllValidator<V>: CompositeValidator<V> {

    constructor(vararg validators: Validator<V>): super(*validators)
    constructor(validators: List<Validator<V>>): super(validators)

    var failingValidator: Validator<V>? = null

    override fun validate(): Boolean {
        failingValidator = validators.firstOrNull { !it.isValid }
        failingValidator?.let {
            return false
        }
        return true
    }

    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create {
        // show the first failing validator error message
        failingValidator?.errorGenerator?.generate() ?: ""
    }

}

