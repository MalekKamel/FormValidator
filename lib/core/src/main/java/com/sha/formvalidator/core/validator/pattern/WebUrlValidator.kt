package com.sha.formvalidator.core.validator.pattern

import android.util.Patterns
import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.ErrorGeneratorInterface

/**
 * Validates a web url in the format:
 * scheme + authority + path
 *
 */
class WebUrlValidator : PatternValidator(Patterns.WEB_URL) {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.webUrl)
}
