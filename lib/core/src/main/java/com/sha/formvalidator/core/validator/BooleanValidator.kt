package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors

class BooleanValidator(val validation: () -> Boolean): AbsValidator<Boolean>() {
    override var value: Boolean? = null
    override fun validate() = value == validation()
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.booleanError)
}