package com.sha.formvalidator.textview.validator

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class SuffixValidator(private val suffix: String, errorMessage: String) : TextValidator(errorMessage) {
    override fun isValid(text: String): Boolean = text.endsWith(suffix)
}
