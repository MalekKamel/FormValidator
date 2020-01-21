package com.sha.formvalidator.rxjava
import org.junit.Test

class RxFormValidatorTest {

    @Test
    fun `should be valid if all valid`() {
        val validator = RxFormValidator(
                FakeValidValidator(),
                FakeValidValidator(),
                FakeValidValidator())
        validator.validate()
                .test()
                .assertNoErrors()
                .assertValue(true)
                .assertValueCount(1)
    }

    @Test
    fun `should be invalid if some invalid`() {
        val validator = RxFormValidator(
                FakeValidValidator(),
                FakeInvalidValidator(),
                FakeInvalidValidator(),
                FakeValidValidator())
        validator.validate()
                .test()
                .assertNoErrors()
                .assertValue(false)
                .assertValueCount(1)
    }

    @Test
    fun `should be invalid if all invalid`() {
        val validator = RxFormValidator(
                FakeInvalidValidator(),
                FakeInvalidValidator(),
                FakeInvalidValidator(),
                FakeInvalidValidator())
        validator.validate()
                .test()
                .assertNoErrors()
                .assertValue(false)
                .assertValueCount(1)
    }

    @Test
    fun `should be invalid if no validators`() {
        val validator = RxFormValidator<com.sha.formvalidator.Validatable>()
        validator.validate()
                .test()
                .assertNoErrors()
                .assertValue(false)
                .assertValueCount(1)
    }

}

class FakeValidValidator: com.sha.formvalidator.Validatable {
    override fun validate(): Boolean = true
}

class FakeInvalidValidator: com.sha.formvalidator.Validatable {
    override fun validate(): Boolean = false
}