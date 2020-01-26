package com.sha.formvalidator.core.validator.pattern

import android.util.Patterns
import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.ErrorGeneratorInterface

/**
 * It validates phone numbers.
 * Regexp was taken from the android source code.
 */
// sdd = space, dot, or dash
// +<digits><sdd>*
// (<digits>)<sdd>*
class PhoneValidator
    : PatternValidator(Patterns.PHONE) {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.phone)

}
