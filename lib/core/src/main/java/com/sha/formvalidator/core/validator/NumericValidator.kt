package com.sha.formvalidator.core.validator

import com.sha.formvalidator.core.DefaultErrors

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class NumericValidator : TextValidator() {
    override var errorMessage: String = DefaultErrors.numericError

    override fun validate(): Boolean {
        return if(value?.isEmpty() == true) false else isDigitsOnly(value!!)
    }

    /**
     * This function is copied from [android.text.TextUtils]
     * to be able to run unit test with mockito
     */
    private fun isDigitsOnly(str: CharSequence): Boolean {
        val len = str.length
        var cp: Int
        var i = 0
        while (i < len) {
            cp = Character.codePointAt(str, i)
            if (!Character.isDigit(cp)) {
                return false
            }
            i += Character.charCount(cp)
        }
        return true
    }
}


