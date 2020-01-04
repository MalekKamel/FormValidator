package com.sha.formvalidator.rxjava

import com.sha.formvalidator.validator.FakeInvalidValidator
import com.sha.formvalidator.validator.FakeValidValidator
import com.sha.formvalidator.Validatable
import org.junit.Test

class RxFormValidatorTest {

    @Test
    fun isValid_allValid() {
        val validator = RxFormValidator(
                com.sha.formvalidator.validator.FakeValidValidator(),
                com.sha.formvalidator.validator.FakeValidValidator(),
                com.sha.formvalidator.validator.FakeValidValidator())
        validator.validate()
                .test()
                .assertNoErrors()
                .assertValue(true)
                .assertValueCount(1)
    }

    @Test
    fun isValid_someInValid() {
        val validator = RxFormValidator(
                com.sha.formvalidator.validator.FakeValidValidator(),
                com.sha.formvalidator.validator.FakeInvalidValidator(),
                com.sha.formvalidator.validator.FakeInvalidValidator(),
                com.sha.formvalidator.validator.FakeValidValidator())
        validator.validate()
                .test()
                .assertNoErrors()
                .assertValue(false)
                .assertValueCount(1)
    }

    @Test
    fun isValid_allInValid() {
        val validator = RxFormValidator(
                com.sha.formvalidator.validator.FakeInvalidValidator(),
                com.sha.formvalidator.validator.FakeInvalidValidator(),
                com.sha.formvalidator.validator.FakeInvalidValidator(),
                com.sha.formvalidator.validator.FakeInvalidValidator())
        validator.validate()
                .test()
                .assertNoErrors()
                .assertValue(false)
                .assertValueCount(1)
    }

    @Test
    fun isValid_empty() {
        val validator = RxFormValidator<com.sha.formvalidator.Validatable>()
        validator.validate()
                .test()
                .assertNoErrors()
                .assertValue(false)
                .assertValueCount(1)
    }

}