package com.sha.formvalidator.validator

import org.junit.Test

class FormValidatorTest {

    @Test
    fun isValid_allValid() {
        val validator = com.sha.formvalidator.FormValidator(
                FakeValidValidator(),
                FakeValidValidator(),
                FakeValidValidator())
        assert(validator.isValid)
    }

    @Test
    fun isValid_someInValid() {
        val validator = com.sha.formvalidator.FormValidator(
                FakeValidValidator(),
                FakeInvalidValidator(),
                FakeInvalidValidator(),
                FakeValidValidator())
        assert(!validator.isValid)
    }

    @Test
    fun isValid_allInValid() {
        val validator = com.sha.formvalidator.FormValidator(
                FakeInvalidValidator(),
                FakeInvalidValidator(),
                FakeInvalidValidator(),
                FakeInvalidValidator())
        assert(!validator.isValid)
    }

    @Test
    fun isValid_empty() {
        val validator = com.sha.formvalidator.FormValidator<com.sha.formvalidator.Validatable>()
        assert(!validator.isValid)
    }

}

class FakeValidValidator: com.sha.formvalidator.Validatable {
    override fun validate(): Boolean = true
}

class FakeInvalidValidator: com.sha.formvalidator.Validatable {
    override fun validate(): Boolean = false
}