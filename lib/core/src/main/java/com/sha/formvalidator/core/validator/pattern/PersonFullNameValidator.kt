package com.sha.formvalidator.core.validator.pattern

import com.sha.formvalidator.core.DefaultErrors

class PersonFullNameValidator : PatternValidator("[\\p{L}- ]+") {
    override var errorMessage: String =  DefaultErrors.personFullNameError
}
