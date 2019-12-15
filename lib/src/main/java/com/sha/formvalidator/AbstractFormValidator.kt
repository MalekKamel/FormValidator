package com.sha.formvalidator

open class AbstractFormValidator<T : Validatable> {
    private var fields: List<T> = emptyList()
    val isValid: Boolean
        get() {
            if(fields.isEmpty()) return false
            var isValid = true
            fields.forEach { isValid = it.validate() && isValid }
            return isValid
        }

    constructor(fields: List<T>) { this.fields = fields }
    @SafeVarargs
    constructor(vararg fields: T) { this.fields = fields.asList() }
}
