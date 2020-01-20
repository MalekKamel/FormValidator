package com.sha.formvalidator.compose

import org.junit.Before
import org.junit.Test

class MandatoryValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.mandatory {  }
        model.value = "x"
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.mandatory {  }
        model.value = ""
        assert(!model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `test showError`() {
        val model = FakeValidation.create()
        model.ignoreInitialValidation = false

        model.showError("error")
        // will be empty after calling isValid
        assert(model.tmpError == "error")

        assert(!model.isValid)
        // should be true, and be false after calling createErrorText()
        assert(model.tmpError == null)
        // must be called to recompose the view
        assert(model.isRecomposeInvoked)

        model.value = "x"

        model.createError()

        // valid as we assigned value x
        assert(model.isValid)
        assert(model.tmpError == null)
    }
}

class OptionalValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid if true and added to compositeValidation`() {
        val model = form + ModelFactory.optional {  }
        model.value = "x"
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be valid if false and added to compositeValidation`() {
        val model = form + ModelFactory.optional {  }
        model.value = ""
        assert(model.isValid)
        assert(!form.isEmpty)
    }
}

class PersonNameValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.personName {  }
        model.value = "Shaban"
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.personName {  }
        model.value = "sha21"
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class PersonFullNameValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.personFullName {  }
        model.value = "Shaban Kamel"
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.personFullName {  }
        model.value = "dd33"
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class DomainValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.domain {  }
        model.value = "google.com"
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.domain {  }
        model.value = "x"
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class AlphaValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.alpha {  }
        model.value = "xyz"
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.alpha {  }
        model.value = "11_-"
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class AlphaNumericValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.alphaNumeric {  }
        model.value = "x1"
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.alphaNumeric {  }
        model.value = "x&"
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class NumericValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.numeric {  }
        model.value = "23"
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.numeric {  }
        model.value = "x"
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class CreditCardValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.creditCard {  }
        model.value = "378734493671000"
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.creditCard {  }
        model.value = "123"
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}


//class OrValidationTest {
//    private lateinit var compositeValidation: CompositeValidation<Validatable>
//
//    @Before
//    fun setup() {
//        compositeValidation = CompositeValidation.create {}
//    }
//
//    @Test
//    fun `should be valid and added to compositeValidation`() {
//        val model = Validation.(compositeValidation) {  }
//        model.value = "x"
//        assert(model.isValid)
//        assert(!compositeValidation.isEmpty())
//    }
//
//    @Test
//    fun `should be invalid and added to compositeValidation`() {
//        val model = Validation.(compositeValidation) {  }
//        model.value = ""
//        assert(!model.isValid)
//        assert(!compositeValidation.isEmpty())
//    }
//}

//class AndValidationTest {
//    private lateinit var compositeValidation: CompositeValidation<Validatable>
//
//    @Before
//    fun setup() {
//        compositeValidation = CompositeValidation.create {}
//    }
//
//    @Test
//    fun `should be valid and added to compositeValidation`() {
//        val model = Validation.(compositeValidation) {  }
//        model.value = "x"
//        assert(model.isValid)
//        assert(!compositeValidation.isEmpty())
//    }
//
//    @Test
//    fun `should be invalid and added to compositeValidation`() {
//        val model = Validation.(compositeValidation) {  }
//        model.value = ""
//        assert(!model.isValid)
//        assert(!compositeValidation.isEmpty())
//    }
//}

class PrefixValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.prefix("x") {  }
        model.value = "x"
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.prefix("x") {  }
        model.value = "y"
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class SuffixValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.suffix("x") {  }
        model.value = "ex"
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.suffix("x") {  }
        model.value = "yy"
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class LongTextRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.longRange(1, 5) {  }
        model.value = 2
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.longRange(1, 5) {  }
        model.value = 7
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class LengthRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.lengthRange(1, 5) {  }
        model.value = "xyz"
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.lengthRange(1, 5) {  }
        model.value = "xyxxyxxyx"
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class FloatRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.floatRange(1f, 5f) {  }
        model.value = 1f
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.floatRange(1f, 5f) {  }
        model.value = 7f
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class DoubleRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.doubleRange(1.0, 5.0) {  }
        model.value = 2.0
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.doubleRange(1.0, 5.0) {  }
        model.value = 7.0
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class LongRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.longRange(1, 5) {  }
        model.value = 1
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.longRange(1, 5) {  }
        model.value = 7
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class IntRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.intRange(1, 5) {  }
        model.value = 2
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.intRange(1, 5) {  }
        model.value = 8
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class CharRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.charRange('a', 'c') {  }
        model.value = 'b'
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.charRange('a', 'c') {  }
        model.value = 'x'
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class ShortRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.shortRange(1, 5) {  }
        model.value = 1
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.shortRange(1, 5) {  }
        model.value = 8
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class ByteValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.byteRange(1, 5) {  }
        model.value = 1
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.byteRange(1, 5) {  }
        model.value = 7
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}

class BooleanValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid and added to compositeValidation`() {
        val model = form + ModelFactory.boolean(true) {  }
        model.value = true
        assert(model.isValid)
        assert(!form.isEmpty)
    }

    @Test
    fun `should be invalid and added to compositeValidation`() {
        val model = form + ModelFactory.boolean(true) {  }
        model.value = false
        assert(!model.isValid)
        assert(!form.isEmpty)
    }
}