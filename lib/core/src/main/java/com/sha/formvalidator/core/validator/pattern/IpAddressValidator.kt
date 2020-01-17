package com.sha.formvalidator.core.validator.pattern

import android.util.Patterns
import com.sha.formvalidator.core.DefaultErrors

/**
 * Validates the ipaddress. The regexp was taken from the android source code.
 *
 */
class IpAddressValidator() : PatternValidator(Patterns.IP_ADDRESS) {
    override var errorMessage: String = DefaultErrors.ipAddressError
}
