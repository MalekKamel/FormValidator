package com.sha.formvalidator.textview.validator.pattern

class PersonNameValidator(errorMessage: String)// will allow people with hyphens in his name or surname. Supports also unicode
    : PatternValidator(errorMessage, "[\\p{L}-]+")
