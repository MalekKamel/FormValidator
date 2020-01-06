package com.sha.formvalidator.core.validator.pattern

class AlphaValidator(errorMessage: String) : PatternValidator(errorMessage, "[A-z\u00C0-\u00ff \\./-\\?]*")