package com.sha.formvalidator.widget

enum class RequiredValidation constructor(var value: String) {
    REQUIRED("required"),
    NOT_REQUIRED("notRequired"),
    NOT_DETECTABLE("");

    companion object {
        fun fromValue(value: String?): RequiredValidation {
            return values().firstOrNull { it.value == value } ?: NOT_DETECTABLE

        }
    }
}