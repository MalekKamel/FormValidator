package com.sha.formvalidator.core.validator.pattern

import com.sha.formvalidator.core.DefaultErrors

class AlphaValidator: PatternValidator("[A-z\u00C0-\u00ff \\./-\\?]*") {
    override var errorMessage: String = DefaultErrors.alphaError
}
