package com.sha.formvalidator.core.validator.pattern

import com.sha.formvalidator.core.DefaultErrors


class AlphaNumericValidator: PatternValidator("[a-zA-Z0-9\u00C0-\u00FF \\./-\\?]*") {
    override var errorMessage: String = DefaultErrors.alphaNumericError
}
