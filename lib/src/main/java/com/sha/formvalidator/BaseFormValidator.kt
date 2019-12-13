package com.sha.formvalidator

open class BaseFormValidator<T : Validatable> {

    private var fields: List<T> = emptyList()
    val isValid: Boolean
        get() {
            var isValid = true
            fields.forEach { isValid = it.validate() && isValid }
            return isValid
        }

    constructor(fields: List<T>) {
        this.fields = fields
    }

    @SafeVarargs
    constructor(vararg fields: T) {
        this.fields = fields.asList()
    }


}
