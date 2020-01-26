package com.sha.formvalidator.core.validator.composite

import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.ErrorGeneratorInterface
import com.sha.formvalidator.core.validator.MandatoryValidator
import com.sha.formvalidator.core.validator.Validator

/**
 * The ALL validator checks if all of the passed validators is returning true.<br></br>
 * Note: the message that will be shown is the one of the first failing validator
 *
 */
class OptionalValidator<V>: AllValidator<V> {

    constructor(vararg validators: Validator<V>): super(*validators)
    constructor(validators: List<Validator<V>>): super(validators)

    private val mandatoryValidator = MandatoryValidator<V>()

    override fun validate(): Boolean {
        mandatoryValidator.value = value
        // if there's no value, it will be valid as it's optional
        if(!mandatoryValidator.isValid) return true
        // there's a value! it will be valid only if all other validators are valid.
        return super.validate()
    }

    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create {
        // show the first failing validator error message
        failingValidator?.errorGenerator?.generate() ?: ""
    }
}