package com.sha.formvalidator.compose

import com.sha.formvalidator.core.validator.MandatoryValidator
import com.sha.formvalidator.core.validator.composite.AllValidator

class FakeValidation(validator: AllValidator<String>): AbsValidationModel<String>() {
    var isRecomposeInvoked = false
    var isValidInvoked = false
    override var recompose: () -> Unit = { isRecomposeInvoked = true }
    override val validator: AllValidator<String> by lazy { validator }
    override var errorMessage: String = "error"
    override val isValid: Boolean
        get() {
           val valid = super.isValid
            isValidInvoked = true
            return valid
        }

    companion object {
        fun create(block: (FakeValidation.() -> Unit)? = null): FakeValidation {
            return FakeValidation(AllValidator(MandatoryValidator())).apply { block?.invoke(this) }
        }
    }
}