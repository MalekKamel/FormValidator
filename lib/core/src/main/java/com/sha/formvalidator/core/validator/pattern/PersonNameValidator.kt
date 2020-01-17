package com.sha.formvalidator.core.validator.pattern

import com.sha.formvalidator.core.DefaultErrors

class PersonNameValidator : PatternValidator("[\\p{L}-]+") {
    override var errorMessage: String = DefaultErrors.personNameError
}
