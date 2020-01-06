package com.sha.formvalidator.compose

import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.ContextAmbient
import com.sha.formvalidator.core.validator.Validator

abstract class AbstractStringModel: AbstractValidatableModel<String>() {
    override var value: String = ""
        set(value) {
            field = value
            validate(false)
        }
}

abstract class AbstractBooleanModel: AbstractValidatableModel<Boolean>() {
    override var value: Boolean = false
        set(value) {
            field = value
            validate(false)
        }
}

abstract class AbstractValidatableModel<V>: ValidatableModel<V> {
    override fun validate(forceValidationOnce: Boolean): Boolean {
        if (forceValidationOnce) this.forceValidationOnce = true
        isValid = validator.isValid(value)
        recompose()
        return isValid
    }
    abstract val validator: Validator<V>
    override var isValid: Boolean = false
    override var validateOnChange: Boolean = false
    override var forceValidationOnce: Boolean = false
    override var recompose: () -> Unit = {}
    override var errorTextRes: Int = -1
        set(value) {
            field = value
            val context = +ambient(ContextAmbient)
            errorText = context.getString(value)
        }
}

interface ValidatableModel<V>: Validatable {
    var value: V
}

interface Validatable: Recomposable {
    var errorText: String
    var errorTextRes: Int
    var isValid: Boolean
    var validateOnChange: Boolean
    var forceValidationOnce: Boolean
    fun validate(forceValidationOnce: Boolean = true): Boolean
}

interface Recomposable {
    var recompose: () -> Unit
}
