package com.sha.formvalidator

import org.junit.Test

class FormValidatorTest {

    @Test
    fun isValid_allValid() {
        val validator = FormValidator(
                listOf(FakeValidValidator(),
                        FakeValidValidator(),
                        FakeValidValidator())
        )
        assert(validator.isValid)
    }

    @Test
    fun isValid_someInValid() {
        val validator = FormValidator(
                listOf(FakeValidValidator(),
                        FakeInvalidValidator(),
                        FakeInvalidValidator(),
                        FakeValidValidator())
        )
        assert(!validator.isValid)
    }

    @Test
    fun isValid_allInValid() {
        val validator = FormValidator(
                listOf(FakeInvalidValidator(),
                        FakeInvalidValidator(),
                        FakeInvalidValidator(),
                        FakeInvalidValidator())
        )
        assert(!validator.isValid)
    }

    @Test
    fun isValid_empty() {
        val validator = FormValidator(emptyList())
        assert(!validator.isValid)
    }

}

class FakeValidValidator: Validatable {
    override fun validate(): Boolean = true
}

class FakeInvalidValidator: Validatable {
    override fun validate(): Boolean = false
}