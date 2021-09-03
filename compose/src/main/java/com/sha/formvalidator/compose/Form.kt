package com.sha.formvalidator.compose

open class Form(private vararg val fields: TextFieldState) {
    fun validate(): Boolean {
        var isValid = true
        for (field in fields) {
            if (!field.isValid) isValid = false
        }
        return isValid
    }
}