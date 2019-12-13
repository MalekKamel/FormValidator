package com.sha.formvalidator.textview.validator

import android.widget.TextView

/**
 * This is a dummy validator. It just returns true on each input.
 *
 */
class DummyValidator : Validator("") {

    override fun isValid(tv: TextView): Boolean {
        return true
    }
}
