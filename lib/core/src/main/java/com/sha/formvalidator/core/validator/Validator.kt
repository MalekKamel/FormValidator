package com.sha.formvalidator.core.validator

interface Validator<V>: ValidatorType {
    var value: V?
    var errorMessage: String
}

interface ValidatorType {
    fun validate(): Boolean
    val isValid: Boolean
        get() = validate()
}