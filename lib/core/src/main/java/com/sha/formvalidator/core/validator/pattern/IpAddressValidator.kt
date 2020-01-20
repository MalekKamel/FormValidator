package com.sha.formvalidator.core.validator.pattern

import android.util.Patterns
import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.ErrorGeneratorInterface

/**
 * Validates the ipaddress. The regexp was taken from the android source code.
 *
 */
class IpAddressValidator : PatternValidator(Patterns.IP_ADDRESS) {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.ipAddressError)
}
