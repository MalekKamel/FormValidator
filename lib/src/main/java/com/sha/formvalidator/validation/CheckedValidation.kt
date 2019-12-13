package com.sha.formvalidator.validation

enum class CheckedValidation constructor(var value: Int) {
    CHECKED(0),
    UNCHECKED(1);

    companion object {
        fun fromValue(value: Int?): CheckedValidation = values().firstOrNull { it.value == value } ?: CHECKED
    }
}