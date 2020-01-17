package com.sha.formvalidator.core.validator

class BooleanValidator(val validation: Boolean): Validator<Boolean> {
    override var isValid: Boolean = false
    override var value: Boolean = validation
    override fun validate() = value == validation
}