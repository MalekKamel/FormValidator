package com.sha.formvalidator.core.text.validator.pattern

class PersonFullNameValidator(errorMessage: String)// will allow people with hyphens in his name or surname. Supports also unicode
    : PatternValidator(errorMessage, "[\\p{L}- ]+")
