package com.sha.formvalidator.compose

import androidx.compose.Model
import com.sha.formvalidator.core.text.validator.RequiredValidator

interface ValidatableTextModel {
    var text: String
    var errorText: String
    var isValid: Boolean
}

@Model
class MandatoryValidation: ValidatableTextModel {
    var value: String = ""
    var errorMessage: String = ""
    override var text: String = value
        set(value) {
            field = value
            isValid = RequiredValidator(value).isValid(value)
        }
    override var errorText: String = ""
    override var isValid: Boolean = false

    companion object {
        fun create(block: MandatoryValidation.() -> Unit): MandatoryValidation {
            return MandatoryValidation().apply(block)
        }
    }
}

@Model
class OptionalValidation: ValidatableTextModel {
    override var text: String = ""
    override var errorText: String = ""
    override var isValid: Boolean = true
}