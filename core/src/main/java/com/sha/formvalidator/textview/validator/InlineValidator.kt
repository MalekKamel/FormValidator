package com.sha.formvalidator.textview.validator

class InlineValidator(
    private val validate: (String) -> Boolean,
    errorMessage: String = ""
) : TextValidator(errorMessage) {

    override fun isValid(text: String): Boolean {
        return validate(text)
    }

}