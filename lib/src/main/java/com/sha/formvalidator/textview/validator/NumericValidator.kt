package com.sha.formvalidator.textview.validator

import android.widget.TextView

/**
 * A validator that returns true only if the input field contains only numbers.
 *
 */
class NumericValidator(errorMessage: String) : TextViewValidator(errorMessage) {

    override fun isValid(tv: TextView): Boolean {
        return if(tv.text.isEmpty()) false else isDigitsOnly(tv.text)
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


