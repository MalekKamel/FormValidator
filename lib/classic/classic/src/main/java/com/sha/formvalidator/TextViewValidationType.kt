package com.sha.formvalidator

enum class TextViewValidationType constructor(var value: Int) {
    REGEX(0),
    NUMERIC(1),
    ALPHA(2),
    ALPHA_NUMERIC(3),
    EMAIL(4),
    CREDIT_CARD(5),
    PHONE(6),
    DOMAIN_NAME(7),
    IP_ADDRESS(8),
    WEB_URL(9),
    NOT_EMPTY(10),
    PERSON_NAME(11),
    PERSON_FULL_NAME(12),
    DATE(13),
    NUMERIC_RANGE(14),
    FLOAT_NUMERIC_RANGE(15),
    NOT_DETECTABLE(2000);

    companion object {
        fun fromValue(value: Int): TextViewValidationType {
           return values().firstOrNull { it.value == value } ?: NOT_DETECTABLE
        }
    }
}
