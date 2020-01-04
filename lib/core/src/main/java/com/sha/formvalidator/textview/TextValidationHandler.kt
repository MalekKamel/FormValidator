package com.sha.formvalidator.textview

import android.content.Context

import com.sha.formvalidator.textview.validator.TextValidator

/**
 * Interface for encapsulating validation of an EditText control
 */
interface TextValidationHandler {

    val isRequired: Boolean
    /**
     * Add a validator to this FormEditText. The validator will be added in the
     * queue of the current validators.
     *
     * @param validator
     */
    fun addValidator(validator: TextValidator)

    /**
     * setup the [TextValidator]s
     */
    fun setupValidator(context: Context)

    /**
     * Calling *validate()* will cause the EditText to go through
     * customValidators and call [.Validator.isValid]
     *
     * @param showError determines if this call should show the UI error.
     * @return true if the validity passes false otherwise.
     */
    fun validate(showError: Boolean = true): Boolean

    fun showError()

}
