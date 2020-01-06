package com.sha.formvalidator.compose

import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.ContextAmbient
import com.sha.formvalidator.core.validator.TextValidator

interface ValidatableModel: ValidatableTextModel, Recomposable {
    var errorText: String
    var errorTextRes: Int
    var isValid: Boolean
    var validateOnChange: Boolean
    var forceValidationOnce: Boolean

    fun validate(): Boolean {
        this.forceValidationOnce = true
        recompose()
        return isValid
    }
}

interface ValidatableTextModel {
    var text: String
}

interface Recomposable {
    var recompose: () -> Unit
}