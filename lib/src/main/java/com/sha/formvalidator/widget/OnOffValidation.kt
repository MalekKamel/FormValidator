package com.sha.formvalidator.widget

enum class OnOffValidation constructor(var value: Int) {
    ON(0),
    OFF(1),
    UNDEFINED(-1);

    companion object {
        fun fromValue(value: Int?): OnOffValidation {
            if (value == null) return UNDEFINED
            return values().firstOrNull { it.value == value } ?: UNDEFINED
        }
    }
}