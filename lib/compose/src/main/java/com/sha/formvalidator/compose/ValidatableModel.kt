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
        if (forceValidationOnce) this.overrideValidateOnChangeOnce = true
        // tmpError is only used when calling showError(), we should remove it here
        // to show the error provided with errorText
        tmpError = ""
        isValid = validator.isValid(value)
        recompose()
        return isValid
    }
    abstract val validator: Validator<V>
    override var isValid: Boolean = false
    override var validateOnChange: Boolean = false
    override var overrideValidateOnChangeOnce: Boolean = false
    override var recompose: () -> Unit = {}
    override var errorTextRes: Int = -1
        set(value) {
            field = value
            val context = +ambient(ContextAmbient)
            errorText = context.getString(value)
        }

    override var tmpError: String = ""

    override fun showError(error: String) {
        isValid = false
        overrideValidateOnChangeOnce = true
        tmpError = error
        recompose()
    }
}

interface ValidatableModel<V>: Validatable {
    var value: V
    fun showError(error: String)
    fun createErrorText(): String? {
        val canValidate = overrideValidateOnChangeOnce || validateOnChange
        overrideValidateOnChangeOnce = false

        if (canValidate && !isValid) {
            // tmpError is only used when calling showError(), and it's removed 
            // in the first call of validate() after showError() is called.
            return if(tmpError.isNotEmpty()) tmpError else errorText
        }
        return null
    }
}

interface Validatable: Recomposable {
    var tmpError: String
    var errorText: String
    var errorTextRes: Int
    var isValid: Boolean
    var validateOnChange: Boolean
    var overrideValidateOnChangeOnce: Boolean
    fun validate(forceValidationOnce: Boolean = true): Boolean
}

interface Recomposable {
    var recompose: () -> Unit
}
