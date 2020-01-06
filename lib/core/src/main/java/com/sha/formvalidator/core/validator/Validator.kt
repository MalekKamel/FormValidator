package com.sha.formvalidator.core.validator

interface Validator<V> {
    fun isValid(value: V): Boolean
}