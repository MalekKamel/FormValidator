package com.sha.formvalidator.model

enum class RequiredValidation constructor(var value: Int) {
    REQUIRED(0),
    NOT_REQUIRED(1);

    companion object {
        fun fromValue(value: Int?) = values().firstOrNull { it.value == value } ?: REQUIRED
    }
}