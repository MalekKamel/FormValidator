package com.sha.formvalidator.core.validator

class WrapValidator<FROM, TO>(
        private val validator: Validator<FROM>,
        private val convertValue: (TO?) -> FROM?
): AbsValidator<TO>() {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create("")
        set(value) {
            field = value
            validator.errorGenerator = value
        }

    override var value: TO? = null
        set(value) {
            field = value
            val v = convertValue(value)
            validator.value = v
        }
    override fun validate() = validator.isValid
}