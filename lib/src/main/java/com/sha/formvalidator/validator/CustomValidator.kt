package com.sha.formvalidator.validator

import android.content.Context

abstract class CustomValidator(errorMessage: String) : Validator(errorMessage) {

    abstract fun customValidationType(context: Context): String

}
