package com.sha.formvalidator

import android.widget.TextView
import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.core.validator.ValueMatchValidator

/**
 * The interface that every field must implement to be validated
 * return true if valid, false otherwise.
 */
interface Validatable {
    fun validate(): Boolean
}

interface TextViewValidatable: Validatable {
    var validationHandler: TextValidationHandler
    val string: String

    /**
     * validate field
     *
     * @return true if valid.
     */
    override fun validate() = validationHandler.validate()

    fun matches(other: TextView, error: String = DefaultErrors.matchError) {
        matches(listOf(other), error)
    }

    fun matches(others: List<TextView>, error: String = DefaultErrors.matchError) {
        this + ValueMatchValidator {
            others.map { it.text.toString() }.toMutableList().apply { add(string) }
        }.apply { errorMessage = error }
    }

    /**
     * Add a validator to this FormEditText. The validator will be added in the
     * queue of the current validators.
     *
     * @param validator object
     */
    fun addValidator(validator: Validator<String>) {
        this.validationHandler.addValidator(validator)
    }

    operator fun plus(validator: Validator<String>) {
        validationHandler.addValidator(validator)
    }
}

