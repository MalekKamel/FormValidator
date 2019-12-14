package com.sha.formvalidator.textview.validator

import android.widget.TextView

/**
 * A validator that returns true only if the text is within the given range.
 *
 */
class LengthRangeValidator(errorMessage: String, private val min: Int, private val max: Int) : TextViewValidator(errorMessage) {

    override fun isValid(tv: TextView): Boolean {
        val length = tv.text.toString().length
        return length in min..max
    }
}
