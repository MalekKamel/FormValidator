package com.sha.formvalidator.core.validator.pattern

import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.ErrorGeneratorInterface

class PersonNameValidator : PatternValidator("[\\p{L}-]+") {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.personNameError)

}
