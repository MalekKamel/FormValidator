package com.sha.formvalidator.textview.validator

import android.widget.EditText
import android.widget.TextView

/**
 * A simple validator that validates the field only if the value is the same as another one.
 *
 */
class ValueMatchValidator(errorMessage: String, vararg ets: EditText) : TextViewValidator(errorMessage) {
    private val ets: List<EditText> = listOf(*ets)

    override fun isValid(tv: TextView): Boolean = ets.all { it.text.toString() == tv.text.toString() }
}
