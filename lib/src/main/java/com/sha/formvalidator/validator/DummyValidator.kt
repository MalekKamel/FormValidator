package com.sha.formvalidator.validator

import android.widget.EditText

/**
 * This is a dummy validator. It just returns true on each input.
 *
 */
class DummyValidator : Validator("") {

    override fun isValid(et: EditText): Boolean {
        return true
    }
}
