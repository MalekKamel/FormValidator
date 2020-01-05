package com.sha.formvalidator.compose

import androidx.compose.State
import androidx.compose.state
import androidx.compose.unaryPlus
import com.sha.formvalidator.core.text.validator.RequiredValidator

interface ValidatableModel {
    var errorText: String
    var isValid: Boolean
    var validateOnChange: Boolean
    var forceValidationOnce: Boolean
    var recompose: () -> Unit

    fun validate(): Boolean {
        this.forceValidationOnce = true
        recompose()
        return isValid
    }
}

interface ValidatableTextModel: ValidatableModel {
    var text: String
}

class MandatoryValidation private constructor(): ValidatableTextModel {
    override var text: String = ""
        set(value) {
            field = value
            isValid = RequiredValidator(value).isValid(value)
        }
    override var errorText: String = ""
    override var isValid: Boolean = false
    override var validateOnChange: Boolean = false
    override var forceValidationOnce: Boolean = false
    override var recompose: () -> Unit = {}

    companion object {
        fun create(block: MandatoryValidation.() -> Unit): State<MandatoryValidation> {
           return +state { MandatoryValidation().apply(block) }
        }
    }
}