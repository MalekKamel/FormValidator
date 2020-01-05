package com.sha.formvalidator.core.validator.pattern

import android.util.Patterns

/**
 * Validates a web url in the format:
 * scheme + authority + path
 *
 */
class WebUrlValidator(errorMessage: String) : PatternValidator(errorMessage, Patterns.WEB_URL)
