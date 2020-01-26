package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors

class ConditionValidator(val condition: () -> Boolean): AbsValidator<Boolean>() {
    override var value: Boolean? = null
    override fun validate() = value == condition()
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.conditionValidation)
}