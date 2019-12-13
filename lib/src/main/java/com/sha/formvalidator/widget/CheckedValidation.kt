package com.sha.formvalidator.widget

enum class CheckedValidation constructor(var value: Int) {
    CHECKED(0),
    UNCHECKED(1),
    UNDEFINED(-1);

    companion object {
        fun fromValue(value: Int?): CheckedValidation {
            if (value == null) return UNDEFINED
            return values().firstOrNull { it.value == value } ?: UNDEFINED
        }
    }
}