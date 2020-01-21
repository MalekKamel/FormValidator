package com.sha.formvalidator

import android.content.Context
import android.text.TextUtils
import com.sha.formvalidator.core.DefaultErrors

class TextViewAttrInfo {
    var errorMessage: String = ""
    var required = true
    var validateOnChange = false
    var validationType: XmlValidationType? = null
    var customValidationType: String = ""

    var regex: String = ""
    var dateFormat: String = ""

    var emptyErrorMessage: String = ""

    var minNumber: Long = 0
    var maxNumber: Long = 0

    var floatMinNumber: Float = 0f
    var floatMaxNumber: Float = 0f

    fun emptyErrorMessage(context: Context): String {
        return if (!TextUtils.isEmpty(emptyErrorMessage))
            emptyErrorMessage else DefaultErrors.mandatoryError
    }
}
