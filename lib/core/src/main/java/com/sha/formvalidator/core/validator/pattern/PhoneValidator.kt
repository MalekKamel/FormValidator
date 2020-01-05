package com.sha.formvalidator.core.validator.pattern

import android.util.Patterns

/**
 * It validates phone numbers.
 * Regexp was taken from the android source code.
 */
class PhoneValidator(errorMessage: String)
// sdd = space, dot, or dash
// +<digits><sdd>*
// (<digits>)<sdd>*
    : PatternValidator(errorMessage, Patterns.PHONE)
