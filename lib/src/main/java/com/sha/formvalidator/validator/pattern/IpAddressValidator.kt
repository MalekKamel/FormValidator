package com.sha.formvalidator.validator.pattern

import android.util.Patterns

/**
 * Validates the ipaddress. The regexp was taken from the android source code.
 *
 */
class IpAddressValidator(errorMessage: String) : PatternValidator(errorMessage, Patterns.IP_ADDRESS)
