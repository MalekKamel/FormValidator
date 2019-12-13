package com.sha.formvalidator.textview.validator

import android.widget.EditText

/**
 * Validator abstract class. To be used with FormEditText
 *
 */
abstract class Validator(var errorMessage: String = "") {

    /**
     * Should check if the EditText is valid.
     *
     * @param et the edittext under evaluation
     * @return true if the edittext is valid, false otherwise
     */
    abstract fun isValid(et: EditText): Boolean

    fun hasErrorMessage(): Boolean {
        return errorMessage.isNotEmpty()
    }
}
