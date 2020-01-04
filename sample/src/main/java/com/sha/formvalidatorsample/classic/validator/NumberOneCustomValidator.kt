package com.sha.formvalidatorsample.classic.validator

import android.content.Context
import com.sha.formvalidator.textview.validator.CustomValidator
import com.sha.formvalidatorsample.R

class NumberOneCustomValidator(errorMessage: String) : CustomValidator(errorMessage) {
    override fun customValidationType(context: Context): String {
        return context.getString(R.string.custom_validator_number_one)
    }
    override fun isValid(text: String) = text == "1"
}
