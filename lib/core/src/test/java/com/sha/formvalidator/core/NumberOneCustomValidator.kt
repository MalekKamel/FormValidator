package com.sha.formvalidator.core

import android.content.Context
import com.sha.formvalidator.core.validator.CustomValidator
import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.ErrorGeneratorInterface

class NumberOneCustomValidator : CustomValidator() {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create("")

    override fun customValidationType(context: Context): String {
        return "Num1"
    }
    override fun validate() = value == "1"
}