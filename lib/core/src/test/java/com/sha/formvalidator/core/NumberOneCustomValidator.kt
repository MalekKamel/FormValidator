package com.sha.formvalidator.core

import android.content.Context
import com.sha.formvalidator.core.validator.CustomValidator

class NumberOneCustomValidator(errorMessage: String) : CustomValidator(errorMessage) {
    override fun customValidationType(context: Context): String {
        return "Num1"
    }
    override fun validate() = value == "1"
}