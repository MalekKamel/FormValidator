package com.sha.formvalidator.textview.validator

import android.widget.EditText

/**
 * A simple validator that validates the field only if the value is the same as another one.
 *
 */
class ValueMatchValidator(errorMessage: String, vararg ets: EditText) : Validator(errorMessage) {
    private val ets: List<EditText> = listOf(*ets)

    override fun isValid(et: EditText): Boolean {
        return ets.all { it.text.toString() == et.text.toString() }
    }
}
