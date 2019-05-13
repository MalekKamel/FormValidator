package com.sha.formvalidator.model;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;

public enum ValidationType {
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

    public int value;

    ValidationType(int value) {
        this.value = value;
    }

    public static ValidationType fromValue(int value){
        Optional<ValidationType> opt = Stream.of(values())
                .filter(item -> item.value == value)
                .findFirst();
       return opt.isPresent() ? opt.get() : NOT_DETECTABLE;
    }
}
