package com.sha.formvalidator.core.validator

class WrapValidator<IN, OUT>(
        private val validator: Validator<IN>,
        private val convertValue: (OUT?) -> IN?
): Validator<OUT> {
    override var errorMessage: String = ""
        set(value) {
            field = value
            validator.errorMessage = value
        }

    override var value: OUT? = null
        set(value) {
            field = value
            val v = convertValue(value)
            validator.value = v
        }
    override fun validate() = validator.isValid
}