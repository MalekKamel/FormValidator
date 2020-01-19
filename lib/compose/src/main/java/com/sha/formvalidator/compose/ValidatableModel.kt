package com.sha.formvalidator.compose

import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.ContextAmbient
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.core.validator.ValueMatchValidator
import com.sha.formvalidator.core.validator.composite.AllValidator

class ValidationModel<V>(validator: AllValidator<V>): AbsValidationModel<V>() {
    override val validator: AllValidator<V> by lazy { validator }

    companion object {
        fun <V> create(validator: AllValidator<V>, block: (ValidationModel<V>.() -> Unit)? = null): ValidationModel<V> {
            return ValidationModel(validator).apply { block?.invoke(this) }
        }

        fun <V> create(validators: List<Validator<V>>, block: (ValidationModel<V>.() -> Unit)? = null): ValidationModel<V> {
            return ValidationModel(AllValidator(validators)).apply { block?.invoke(this) }
        }

        fun <V> create(validator: Validator<V>, block: (ValidationModel<V>.() -> Unit)? = null): ValidationModel<V> {
            return ValidationModel(AllValidator(validator)).apply { block?.invoke(this) }
        }
    }
}

abstract class AbsValidationModel<V>: ValidatableModel<V> {
    override var value: V? = null
        set(value) {
            field = value
            validator.value = value
            validate(false)
        }
    override var ignoreInitialValidation: Boolean = true
    override var errorMessage: String = ""

    override var validateOnChange: Boolean = false
    override var overrideValidateOnChangeOnce: Boolean = false
    override var recompose: () -> Unit = {}
    override var errorMessageRes: Int = -1
        set(value) {
            field = value
            val context = +ambient(ContextAmbient)
            errorMessage = context.getString(value)
        }

    override var tmpError: String = ""

    override var onValidate: ((Boolean) -> Unit)? = null

    override var isMandatory: Boolean = true

    override var isIgnored: Boolean = false

    override var tag: String? = null

    override var shouldIgnore: (() -> Boolean)? = null

    override var status: ModelStatus = ModelStatus.INVALID

    override fun validate(overrideValidateOnChangeOnce: Boolean): Boolean {
        if (isIgnored) return true

        if (overrideValidateOnChangeOnce) this.overrideValidateOnChangeOnce = true
        // tmpError is only used when calling showError(), we should remove it here
        // to show the error provided with errorText
        tmpError = ""

        val valid = validator.isValid

        onValidate?.invoke(valid)
        status = if(valid) ModelStatus.VALID else ModelStatus.INVALID

        recompose()

        return valid
    }

    override fun showError(error: String) {
        overrideValidateOnChangeOnce = true
        tmpError = error
        recompose()
    }

    override fun matches(
            model: ValidatableModel<V>,
            errorMessage: String
    ): ValidatableModel<V> {
        return matches(listOf(model), errorMessage)
    }

    override fun matches(
            models: List<ValidatableModel<V>>,
            errorMessage: String
    ): ValidatableModel<V> {
        val allModels = models.toMutableList().apply { add(0, this@AbsValidationModel) }
        val matchValidator = ValueMatchValidator {
            val values = allModels.map { it.value }
            return@ValueMatchValidator values
        }

        // notify each model with the error
        matchValidator.addOnValidateListener { valid ->
            allModels.forEach {
                it.status = if (valid) ModelStatus.VALID else ModelStatus.INVALID
                if (!valid) it.showError(errorMessage)
                else it.recompose()
            }
        }

        // each model should have this validator
        allModels.forEach { it.addValidator(matchValidator) }
        return this
    }
}

interface ValidatableModel<V>: Validatable {
    var value: V?
    val validator: AllValidator<V>
    var isIgnored: Boolean
    var status: ModelStatus
    var tag: String?
    var shouldIgnore: (() -> Boolean)?

    fun createError(): String? {
        if (isIgnored) return null
        if (shouldIgnore?.invoke() == true) return null

        if (ignoreInitialValidation) {
            ignoreInitialValidation = false
            return null
        }
        val canValidate = overrideValidateOnChangeOnce || validateOnChange
        overrideValidateOnChangeOnce = false

        if (canValidate && status == ModelStatus.INVALID)
            return if(tmpError.isNotEmpty()) tmpError else errorMessage

        return null
    }

    fun matches(
            model: ValidatableModel<V>,
            errorMessage: String): ValidatableModel<V>

    fun matches(
            models: List<ValidatableModel<V>>,
            errorMessage: String): ValidatableModel<V>

    infix fun addTo(formValidation: FormValidation): ValidatableModel<V> {
        formValidation + this
        return this
    }

    fun addValidator(other: Validator<V>): ValidatableModel<V> {
        validator + other
        return this
    }

}

interface Validatable: Recomposable {

    var errorMessage: String
    var errorMessageRes: Int
    val isValid: Boolean
        get() = validate(true)
    var ignoreInitialValidation: Boolean
    var validateOnChange: Boolean
    var overrideValidateOnChangeOnce: Boolean
    var onValidate: ((Boolean) -> Unit)?
    var isMandatory: Boolean

    /**
     * This value is only used when calling showError(), and it's removed
     * in the first call of isValid after showError() is called.
     */
    var tmpError: String

    fun validate(overrideValidateOnChangeOnce: Boolean = true): Boolean
    fun showError(error: String)
}

interface Recomposable {
    var recompose: () -> Unit
}

enum class ModelStatus {
    VALID, INVALID
}