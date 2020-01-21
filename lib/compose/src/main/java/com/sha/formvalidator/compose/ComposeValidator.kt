package com.sha.formvalidator.compose

open class ComposeValidator {
    internal var models: List<ValidatableModel<*>> = emptyList()
    val isValid: Boolean
        get() {
            if(models.isEmpty()) return false
            var isValid = true
            models.forEach {
                val fieldValid = it.isValid
                isValid = fieldValid && isValid
                it.validate()
            }
            return isValid
        }
    /**
     * create an instance with list of fields to be validated.
     */
    constructor(form: Form) {
        this.models = form.list
    }

    /**
     * create an instance with list of fields to be validated.
     */
    constructor(models: List<ValidatableModel<*>>) {
        this.models = models
    }

    /**
     * create an instance with var args of fields to be
     * validated.
     */
    @SafeVarargs
    constructor(vararg models: ValidatableModel<*>) {
        this.models = models.asList()
    }
}
