package com.sha.formvalidator.core.validator

class BooleanValidator(val validation: Boolean): Validator<Boolean> {
    override fun isValid(value: Boolean) = value == validation
}