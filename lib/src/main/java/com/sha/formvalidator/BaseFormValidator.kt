package com.sha.formvalidator

open class BaseFormValidator<T : Validatable> {

    private var fields: List<T> = emptyList()

    protected val isValidForm: Boolean
        get() = fields.all { it.validate() }

    constructor(fields: List<T>) {
        this.fields = fields
    }

    @SafeVarargs
    constructor(vararg fields: T) {
        this.fields = fields.asList()
    }


}
