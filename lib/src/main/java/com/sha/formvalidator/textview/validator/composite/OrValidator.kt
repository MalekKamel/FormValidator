package com.sha.formvalidator.textview.validator.composite

import android.widget.TextView
import com.sha.formvalidator.textview.validator.Validator

/**
 * The or validator checks if one of passed validators is returning true.<br></br>
 * Validator's priority is maintained by index, the lower index is the higher priority.
 * Note: the message that will be shown is the one passed to the Constructor
 *
 */
class OrValidator(message: String, vararg validators: Validator) : CompositeValidator(message, *validators) {

    override fun isValid(tv: TextView): Boolean {
        for (v in validators)
            if (v.isValid(tv)) return true // Remember :) We're acting like an || operator.

        return false
    }

}

