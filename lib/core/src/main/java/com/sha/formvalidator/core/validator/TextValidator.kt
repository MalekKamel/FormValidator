package com.sha.formvalidator.core.validator

abstract class TextValidator(var errorMessage: String = ""): Validator<String> {

    override var value: String = ""
}
