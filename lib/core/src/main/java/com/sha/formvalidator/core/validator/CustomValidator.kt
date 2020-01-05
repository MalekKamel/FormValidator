package com.sha.formvalidator.core.validator

import android.content.Context

abstract class CustomValidator(errorMessage: String) : TextValidator(errorMessage) {
    abstract fun customValidationType(context: Context): String
}
