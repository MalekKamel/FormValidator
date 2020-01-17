package com.sha.formvalidator.core.validator.pattern

import android.util.Patterns
import com.sha.formvalidator.core.DefaultErrors

/**
 * It validates phone numbers.
 * Regexp was taken from the android source code.
 */
// sdd = space, dot, or dash
// +<digits><sdd>*
// (<digits>)<sdd>*
class PhoneValidator
    : PatternValidator(Patterns.PHONE) {
    override var errorMessage: String = DefaultErrors.phoneError
}
