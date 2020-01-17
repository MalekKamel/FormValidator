package com.sha.formvalidator.core.validator

interface Validator<V> {
    var value: V
    val isValid: Boolean
        get() = validate()
    fun validate(): Boolean
}