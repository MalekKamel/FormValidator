package com.sha.formvalidator.textview.validator

import android.content.Context

abstract class CustomValidator(errorMessage: String) : TextViewValidator(errorMessage) {
    abstract fun customValidationType(context: Context): String
}
