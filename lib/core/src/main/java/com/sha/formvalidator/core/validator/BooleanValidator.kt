package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors

class BooleanValidator(val validation: Boolean): Validator<Boolean> {
    override var value: Boolean = validation
    override fun validate() = value == validation
    override var errorMessage: String = DefaultErrors.booleanError
}