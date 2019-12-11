package com.sha.formvalidatorsample.validator

import android.content.Context
import android.widget.EditText

import com.sha.formvalidator.validator.CustomValidator
import com.sha.formvalidatorsample.R

class NumberOneCustomValidator(errorMessage: String) : CustomValidator(errorMessage) {

    override fun customValidationType(context: Context): String {
        return context.getString(R.string.custom_validator_number_one)
    }

    override fun isValid(et: EditText): Boolean {
        return et.text.toString() == "1"
    }

}
