package com.sha.formvalidator.compose

import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.ContextAmbient
import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.ErrorGeneratorInterface
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.core.validator.ValueMatchValidator
import com.sha.formvalidator.core.validator.composite.AllValidator

class ValidationModel<V>(validator: AllValidator<V>): AbsValidatableModel<V>() {
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

abstract class AbsValidatableModel<V>: ValidatableModel<V> {
    override var value: V? = null
        set(value) {
            field = value
            validator.value = value
            validate(ValidationSource.LIBRARY)
        }
    override var errorMessageRes: Int = -1
        set(value) {
            field = value
            val context = +ambient(ContextAmbient)
            errorMessage = context.getString(value)
        }
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create {
        if (errorMessage.isNotEmpty()) errorMessage else validator.errorGenerator.generate()
    }
    override var ignoreInitialValidation: Boolean = true
    override var errorMessage: String = ""
    override var validateOnChange: Boolean = false
    override var validationSource: ValidationSource = ValidationSource.LIBRARY
    override var recompose: () -> Unit = {}
    override var tmpError: String? = null
    override var onValidate: ((Boolean) -> Unit)? = null
    override var isMandatory: Boolean = true
    override var isIgnored: Boolean = false
    override var tag: String? = null
    override var shouldIgnore: (() -> Boolean)? = null
    override var status: ModelStatus = ModelStatus.INVALID

    override fun validate(validationSource: ValidationSource): Boolean {
        if (isIgnored) return true
        this.validationSource = validationSource

        // tmpError is only used when calling showError(), we should remove it here
        // to show the error provided with errorText
        tmpError = null

        val valid = validator.isValid

        onValidate?.invoke(valid)
        status = if(valid) ModelStatus.VALID else ModelStatus.INVALID

        recompose()

        return valid
    }

    override fun showError(error: String) {
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
        val allModels = models.toMutableList().apply { add(0, this@AbsValidatableModel) }
        val matchValidator = ValueMatchValidator {
            // when return directly it returns List<ValidatableModel<V>, don't know why!
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

        allModels.forEach {
            // each model should have this validator
            it.addValidator(matchValidator)
            // validate on change to reflect the error immediately
            it.validateOnChange = true
        }
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
        if (status == ModelStatus.VALID) return null

        if (isIgnored) return null
        if (shouldIgnore?.invoke() == true) return null

        if (ignoreInitialValidation) {
            ignoreInitialValidation = false
            return null
        }

        val canShowError = when(validationSource) {
            // user invoked this validation, so we should show error.
            ValidationSource.USER -> true
            // This validation is invoked from the library (when the value changes), we
            // should show error if it's allowed to validate on change only
            ValidationSource.LIBRARY -> validateOnChange
        }

        if (!canShowError) return null

        return if(!tmpError.isNullOrEmpty()) tmpError else errorGenerator.generate()
    }

    fun matches(
            model: ValidatableModel<V>,
            errorMessage: String): ValidatableModel<V>

    fun matches(
            models: List<ValidatableModel<V>>,
            errorMessage: String): ValidatableModel<V>

    fun addTo(form: Form): ValidatableModel<V> {
        form + this
        return this
    }

    fun addValidator(other: Validator<V>): ValidatableModel<V> {
        validator + other
        return this
    }

    fun addValidator(index: Int, other: Validator<V>): ValidatableModel<V> {
        validator.validators.add(index, other)
        return this
    }

    fun removeValidator(other: Validator<V>): ValidatableModel<V> {
        validator - other
        return this
    }

    fun removeValidator(index: Int): ValidatableModel<V> {
        validator.validators.removeAt(index)
        return this
    }

}

interface Validatable: Recomposable {
    var errorGenerator: ErrorGeneratorInterface
    var errorMessage: String
    var errorMessageRes: Int
    val isValid: Boolean
        get() = validate()
    var ignoreInitialValidation: Boolean
    var validateOnChange: Boolean
    var onValidate: ((Boolean) -> Unit)?
    var isMandatory: Boolean
    var validationSource: ValidationSource
    /**
     * This value is only used when calling showError(), and it's removed
     * in the first call of isValid after showError() is called.
     */
    var tmpError: String?

    fun validate(validationSource: ValidationSource = ValidationSource.USER): Boolean
    fun showError(error: String)
}

interface Recomposable {
    var recompose: () -> Unit
}

enum class ModelStatus {
    VALID, INVALID
}

enum class ValidationSource {
    USER, LIBRARY
}