package com.sha.formvalidator.core.validator.composite

import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.TextValidator

/**
 * The or validator checks if one of passed validators is returning true.<br></br>
 * Validator's priority is maintained by index, the lower index is the higher priority.
 * Note: the message that will be shown is the one passed to the Constructor
 *
 */
class OrValidator : CompositeValidator {
    override var errorMessage: String = DefaultErrors.orError

    constructor(vararg validators: TextValidator): super(*validators)
    constructor(validators: List<TextValidator>): super(validators)

    override fun validate(): Boolean {
        for (v in validators) if (v.isValid) return true
        return false
    }

}

