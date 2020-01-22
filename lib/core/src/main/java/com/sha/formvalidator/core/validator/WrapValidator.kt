package com.sha.formvalidator.core.validator

class WrapValidator<IN, OUT>(
        private val validator: Validator<OUT>,
        private val convertValue: (IN?) -> OUT?
): AbsValidator<IN>() {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create("")
        set(value) {
            field = value
            validator.errorGenerator = value
        }

    override var value: IN? = null
        set(value) {
            field = value
            val v = convertValue(value)
            validator.value = v
        }
    override fun validate() = validator.isValid
}