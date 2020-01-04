package com.sha.formvalidator.core.text.validator

import android.widget.TextView

/**
 * Validator abstract class. To be used with FormEditText
 *
 */
abstract class TextValidator(var errorMessage: String = "") {

    /**
     * Should check if the [TextView] is valid.
     *
     * @param text the [TextView] under evaluation
     * @return true if the edittext is valid, false otherwise
     */
    abstract fun isValid(text: String): Boolean

    fun hasErrorMessage(): Boolean = errorMessage.isNotEmpty()
}
