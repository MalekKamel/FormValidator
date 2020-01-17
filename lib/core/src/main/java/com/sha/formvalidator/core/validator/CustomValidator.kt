package com.sha.formvalidator.core.validator

import android.content.Context

abstract class CustomValidator : TextValidator() {
    abstract fun customValidationType(context: Context): String
}
