package com.sha.formvalidator.core.text.validator

/**
 * This is a dummy validator. It just returns true on each input.
 *
 */
class DummyValidator : TextValidator("") {
    override fun isValid(text: String): Boolean  = true
}
