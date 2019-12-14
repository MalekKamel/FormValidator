package com.sha.formvalidator.textview.validator

import android.widget.TextView

/**
 * A validator that returns true only if the input field contains only numbers
 * and the number is within the given range.
 *
 * @author Said Tahsin Dane <tasomaniac></tasomaniac>@gmail.com>
 */
class FloatNumericRangeValidator(errorMessage: String, private val floatMin: Double, private val floatMax: Double) : TextViewValidator(errorMessage) {
    override fun isValid(tv: TextView): Boolean {
       return try { tv.text.toString().toDouble() in floatMin..floatMax }
        catch (e: NumberFormatException) { false }
    }
}
