package com.sha.formvalidator.core.validator.composite

import com.sha.formvalidator.core.validator.TextValidator

/**
 * The or validator checks if one of passed validators is returning true.<br></br>
 * Validator's priority is maintained by index, the lower index is the higher priority.
 * Note: the message that will be shown is the one passed to the Constructor
 *
 */
class OrValidator(message: String, vararg validators: TextValidator) : CompositeValidator(message, *validators) {

    override fun isValid(text: String): Boolean {
        for (v in validators)
            if (v.isValid(text)) return true // Remember :) We're acting like an || operator.

        return false
    }

}

