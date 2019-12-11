package com.sha.formvalidator.model

import android.content.Context
import android.text.TextUtils

import com.sha.formvalidator.R

class Options {
    var errorMessage: String = ""
    var required = true
    var validationType: ValidationType? = null
    var customValidationType: String = ""

    var regex: String = ""
    var dateFormat: String = ""

    var emptyErrorMessage: String = ""

    var minNumber: Int = 0
    var maxNumber: Int = 0

    var floatMinNumber: Float = 0.toFloat()
    var floatMaxNumber: Float = 0.toFloat()

    fun emptyErrorMessage(context: Context): String {
        return if (!TextUtils.isEmpty(emptyErrorMessage))
            emptyErrorMessage
        else
            context.getString(R.string.required)
    }
}
