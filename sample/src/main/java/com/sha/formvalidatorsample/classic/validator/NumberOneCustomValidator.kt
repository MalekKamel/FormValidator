package com.sha.formvalidatorsample.classic.validator

import android.content.Context
import com.sha.formvalidator.core.validator.CustomValidator
import com.sha.formvalidatorsample.R

class NumberOneCustomValidator : CustomValidator() {
    override var errorMessage: String = "Value doesn't equal 1"
    override fun customValidationType(context: Context): String {
        return context.getString(R.string.custom_validator_number_one)
    }
    override fun validate() = value == "1"
}
