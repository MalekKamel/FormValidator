package com.sha.formvalidator.validator

import android.content.Context
import android.widget.TextView
import com.sha.formvalidator.textview.validator.CustomValidator

class NumberOneCustomValidator(errorMessage: String) : CustomValidator(errorMessage) {

    override fun customValidationType(context: Context): String {
        return "Num1"
    }

    override fun isValid(tv: TextView) = tv.text.toString() == "1"

}