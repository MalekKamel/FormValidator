package com.sha.formvalidator

/**
 * The interface that every field must implement to be validated
 * return true if valid, false otherwise.
 */
interface Validatable {
    fun validate(): Boolean
}
