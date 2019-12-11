package com.sha.formvalidator.validator

import android.widget.EditText

/**
 * A simple validator that validates the field only if the value is the same as another one.
 *
 */
class ValueMatchValidator(errorMessage: String, vararg ets: EditText) : Validator(errorMessage) {
    private val ets: List<EditText> = listOf(*ets)

    override fun isValid(et: EditText): Boolean {
        val value = et.text.toString()
        for (et in ets)
            if (et.text.toString() != value)
                return false
        return true
    }
}
