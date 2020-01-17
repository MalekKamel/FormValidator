package com.sha.formvalidator.core.validator

/**
 * This is a dummy validator. It just returns true on each input.
 *
 */
class DummyValidator : TextValidator("") {
    override fun validate(): Boolean  = true
}
