package com.sha.formvalidator.core.validator

import android.content.Context

abstract class CustomValidator : AbsValidator<String>() {
    abstract fun customValidationType(context: Context): String
}
