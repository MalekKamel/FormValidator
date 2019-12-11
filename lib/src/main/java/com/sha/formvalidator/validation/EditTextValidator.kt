package com.sha.formvalidator.validation

import android.content.Context

import com.sha.formvalidator.validator.Validator

/**
 * Interface for encapsulating validation of an EditText control
 */
interface EditTextValidator {

    val isRequired: Boolean
    /**
     * Add a validator to this FormEditText. The validator will be added in the
     * queue of the current validators.
     *
     * @param validator
     */
    fun addValidator(validator: Validator)

    /**
     * setup the [Validator]s
     */
    fun setupValidator(context: Context)

    /**
     * Calling *validate()* will cause the EditText to go through
     * customValidators and call [.Validator.isValid]
     * Same as [.validate] with first parameter true
     *
     * @return true if the validity passes false otherwise.
     */
    fun validate(): Boolean

    /**
     * Calling *validate()* will cause the EditText to go through
     * customValidators and call [.Validator.isValid]
     *
     * @param showError determines if this call should show the UI error.
     * @return true if the validity passes false otherwise.
     */
    fun validate(showError: Boolean): Boolean

    fun showError()

}
