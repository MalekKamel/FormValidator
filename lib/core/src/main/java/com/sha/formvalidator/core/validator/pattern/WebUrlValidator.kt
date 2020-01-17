package com.sha.formvalidator.core.validator.pattern

import android.util.Patterns
import com.sha.formvalidator.core.DefaultErrors

/**
 * Validates a web url in the format:
 * scheme + authority + path
 *
 */
class WebUrlValidator : PatternValidator(Patterns.WEB_URL) {
    override var errorMessage: String = DefaultErrors.webUrlError
}
