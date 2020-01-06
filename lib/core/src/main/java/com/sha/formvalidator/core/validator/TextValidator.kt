package com.sha.formvalidator.core.validator

import android.widget.TextView

/**
 * Validator abstract class. To be used with FormEditText
 *
 */
abstract class TextValidator(var errorMessage: String = ""): Validator<String> {

    /**
     * Should check if the [TextView] is valid.
     *
     * @param value the [TextView] under evaluation
     * @return true if the edittext is valid, false otherwise
     */
    abstract override fun isValid(value: String): Boolean

    fun hasErrorMessage(): Boolean = errorMessage.isNotEmpty()
}
