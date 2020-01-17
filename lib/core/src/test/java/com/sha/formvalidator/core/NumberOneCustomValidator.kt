package com.sha.formvalidator.core

import android.content.Context
import com.sha.formvalidator.core.validator.CustomValidator

class NumberOneCustomValidator : CustomValidator() {
    override var errorMessage: String = ""

    override fun customValidationType(context: Context): String {
        return "Num1"
    }
    override fun validate() = value == "1"
}