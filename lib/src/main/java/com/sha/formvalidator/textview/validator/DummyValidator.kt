package com.sha.formvalidator.textview.validator

import android.widget.TextView

/**
 * This is a dummy validator. It just returns true on each input.
 *
 */
class DummyValidator : TextViewValidator("") {
    override fun isValid(tv: TextView): Boolean  = true
}
