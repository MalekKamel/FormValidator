package com.sha.formvalidator.validator.pattern;

import android.util.Patterns;

/**
 * Validates the ipaddress. The regexp was taken from the android source code.
 *
 */
public class IpAddressValidator extends PatternValidator {
    public IpAddressValidator(String errorMessage) {
        super(errorMessage, Patterns.IP_ADDRESS);
    }
}
