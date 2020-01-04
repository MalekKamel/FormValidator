package com.sha.formvalidator.model

enum class OnOffValidation constructor(var value: Int) {
    ON(0),
    OFF(1);

    companion object {
        fun fromValue(value: Int?): OnOffValidation = values().firstOrNull { it.value == value } ?: ON
    }
}