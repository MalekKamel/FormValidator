package com.sha.formvalidator.textview.validator.pattern


class AlphaNumericValidator(errorMessage: String) : PatternValidator(errorMessage, "[a-zA-Z0-9\u00C0-\u00FF \\./-\\?]*")
