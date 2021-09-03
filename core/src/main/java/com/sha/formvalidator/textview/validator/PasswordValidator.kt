package com.sha.formvalidator.textview.validator

import com.sha.formvalidator.textview.validator.pattern.PatternValidator

class PasswordValidator(
    errorMessage: String,
    regex: String = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$"
) : PatternValidator(
    errorMessage = errorMessage,
    regex = regex
)