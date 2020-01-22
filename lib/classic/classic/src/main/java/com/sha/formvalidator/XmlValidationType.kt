package com.sha.formvalidator

enum class XmlValidationType constructor(var value: Int) {
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
    MANDATORY(10),
    PERSON_NAME(11),
    PERSON_FULL_NAME(12),
    DATE(13),
    TEXT_LENGTH(14),
    FLOAT_RANGE(15),
    INT_RANGE(16),
    OPTIONAL(17),
    UNKNOWN(2000);

    companion object {
        fun fromValue(value: Int): XmlValidationType {
           return values().firstOrNull { it.value == value } ?: UNKNOWN
        }
    }
}
