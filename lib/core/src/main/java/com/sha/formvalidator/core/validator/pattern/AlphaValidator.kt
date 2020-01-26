package com.sha.formvalidator.core.validator.pattern

import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.ErrorGeneratorInterface

class AlphaValidator: PatternValidator("[A-z\u00C0-\u00ff \\./-\\?]*") {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.alpha)
}
