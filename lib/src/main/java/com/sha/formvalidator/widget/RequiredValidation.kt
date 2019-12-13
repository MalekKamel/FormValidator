package com.sha.formvalidator.widget

enum class RequiredValidation constructor(var value: Int) {
    REQUIRED(0),
    NOT_REQUIRED(1),
    UNDEFINED(-1);

    companion object {
        fun fromValue(value: Int?): RequiredValidation {
            return values().firstOrNull { it.value == value } ?: UNDEFINED

        }
    }
}