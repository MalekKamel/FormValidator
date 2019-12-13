package com.sha.formvalidatorsample.validator

import android.content.Context
import android.widget.TextView
import com.sha.formvalidator.textview.validator.CustomValidator
import com.sha.formvalidatorsample.R

class NumberOneCustomValidator(errorMessage: String) : CustomValidator(errorMessage) {

    override fun customValidationType(context: Context): String {
        return context.getString(R.string.custom_validator_number_one)
    }

    override fun isValid(tv: TextView): Boolean {
        return tv.text.toString() == "1"
    }

}
