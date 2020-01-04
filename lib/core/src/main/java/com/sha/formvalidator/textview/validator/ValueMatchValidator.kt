package com.sha.formvalidator.textview.validator

/**
 * A simple validator that validates the field only if the value is the same as another one.
 *
 */
class ValueMatchValidator(errorMessage: String, vararg texts: String) : TextValidator(errorMessage) {
    private val tvs: List<String> = listOf(*texts)

    override fun isValid(text: String): Boolean = tvs.all { it == text }
}
