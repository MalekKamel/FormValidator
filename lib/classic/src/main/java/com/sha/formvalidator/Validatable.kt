package com.sha.formvalidator

import android.view.View
import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.core.validator.ValueMatchValidator
import com.sha.formvalidator.handler.ValidationHandlerInterface

/**
 * The interface that every field must implement to be validated
 * return true if valid, false otherwise.
 */
interface Validatable {
    fun validate(): Boolean
}

interface ValidatableWidget<V>: Validatable {
    var validationHandler: ValidationHandlerInterface<V>
    val value: V?

    /**
     * validate field
     *
     * @return true if valid.
     */
    override fun validate() = validationHandler.validate()

    fun matches(other: ValidatableWidget<V>, error: String = DefaultErrors.matchError) {
        matches(listOf(other), error)
    }

    fun matches(others: List<ValidatableWidget<V>>, error: String = DefaultErrors.matchError) {
        this + ValueMatchValidator {
            others.map { it.value }
        }.apply { errorMessage = error }
    }

    /**
     * Add a validator to this FormEditText. The validator will be added in the
     * queue of the current validators.
     *
     * @param validator object
     */
    fun addValidator(validator: Validator<V>) {
        this.validationHandler.addValidator(validator)
    }

    operator fun plus(validator: Validator<V>) {
        validationHandler.addValidator(validator)
    }
}
