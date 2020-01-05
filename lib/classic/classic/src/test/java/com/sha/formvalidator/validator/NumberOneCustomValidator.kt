package com.sha.formvalidator.validator

import android.content.Context
import com.sha.formvalidator.core.validator.CustomValidator

class NumberOneCustomValidator(errorMessage: String) : CustomValidator(errorMessage) {
    override fun customValidationType(context: Context): String {
        return "Num1"
    }
    override fun isValid(text: String) = text == "1"
}