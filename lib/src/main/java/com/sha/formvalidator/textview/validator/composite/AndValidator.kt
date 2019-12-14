package com.sha.formvalidator.textview.validator.composite

import android.widget.TextView
import com.sha.formvalidator.textview.validator.TextViewValidator

/**
 * The AND validator checks if all of the passed validators is returning true.<br></br>
 * Note: the message that will be shown is the one of the first failing validator
 *
 */
class AndValidator(vararg validators: TextViewValidator) : CompositeValidator("", *validators) {

    override fun isValid(tv: TextView): Boolean {
       val anyFails = validators.firstOrNull { !it.isValid(tv) }
        anyFails?.let {
            // error message equals the first failing validator
            this.errorMessage = it.errorMessage
            return false // Remember :) We're acting like an || operator.
        }
        // true if no one fails
        return true
    }
}

