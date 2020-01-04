package com.sha.formvalidator.core.text

import android.content.Context
import android.text.TextUtils
import com.sha.formvalidator.core.R

class TextViewAttrInfo {
    var errorMessage: String = ""
    var required = true
    var validationType: TextViewValidationType? = null
    var customValidationType: String = ""

    var regex: String = ""
    var dateFormat: String = ""

    var emptyErrorMessage: String = ""

    var minNumber: Int = 0
    var maxNumber: Int = 0

    var floatMinNumber: Float = 0f
    var floatMaxNumber: Float = 0f

    fun emptyErrorMessage(context: Context): String {
        return if (!TextUtils.isEmpty(emptyErrorMessage))
            emptyErrorMessage else context.getString(R.string.required)
    }
}
