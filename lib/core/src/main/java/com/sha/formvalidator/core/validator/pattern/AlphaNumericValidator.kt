package com.sha.formvalidator.core.validator.pattern

import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.ErrorGeneratorInterface


class AlphaNumericValidator: PatternValidator("[a-zA-Z0-9\u00C0-\u00FF \\./-\\?]*") {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.alphaNumericError)
}
