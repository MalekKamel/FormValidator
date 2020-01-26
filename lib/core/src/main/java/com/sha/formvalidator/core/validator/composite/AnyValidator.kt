package com.sha.formvalidator.core.validator.composite

import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.ErrorGeneratorInterface
import com.sha.formvalidator.core.validator.Validator

/**
 * The or validator checks if one of passed validators is returning true.<br></br>
 * Validator's priority is maintained by index, the lower index is the higher priority.
 * Note: the message that will be shown is the one passed to the Constructor
 *
 */
open class AnyValidator<V> : CompositeValidator<V> {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.anyValidation)

    constructor(vararg validators: Validator<V>): super(*validators)
    constructor(validators: List<Validator<V>>): super(validators)

    override fun validate(): Boolean {
        for (v in validators) if (v.isValid) return true
        return false
    }

}

