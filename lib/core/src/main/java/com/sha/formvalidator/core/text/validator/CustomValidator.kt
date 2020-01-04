package com.sha.formvalidator.core.text.validator

import android.content.Context

abstract class CustomValidator(errorMessage: String) : TextValidator(errorMessage) {
    abstract fun customValidationType(context: Context): String
}
