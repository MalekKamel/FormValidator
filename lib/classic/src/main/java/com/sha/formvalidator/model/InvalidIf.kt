package com.sha.formvalidator.model

enum class InvalidIf constructor(var value: Int) {
    FIRST_ITEM_SELECTED(0),
    NULLABLE(1),
    UNKNOWN(2000);

    companion object {
        fun fromValue(value: Int): InvalidIf {
            return values().firstOrNull { it.value == value } ?: UNKNOWN
        }
    }
}
