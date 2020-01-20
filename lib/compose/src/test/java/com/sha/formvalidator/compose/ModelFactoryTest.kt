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
    fun `should be valid`() {
        val model = form + mandatory()
        model.value = "x"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + mandatory()
        model.value = ""
        assert(!model.isValid)
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
    fun `should be valid if true`() {
        val model = form + optional()
        model.value = "x"
        assert(model.isValid)
    }

    @Test
    fun `should be valid if false`() {
        val model = form + optional()
        model.value = ""
        assert(model.isValid)
    }
}

class PersonNameValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + personName()
        model.value = "Shaban"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + personName()
        model.value = "sha21"
        assert(!model.isValid)
    }
}

class PersonFullNameValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + personFullName()
        model.value = "Shaban Kamel"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + personFullName()
        model.value = "dd33"
        assert(!model.isValid)
    }
}

class DomainValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + domain()
        model.value = "google.com"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + domain()
        model.value = "x"
        assert(!model.isValid)
    }
}

class AlphaValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + alpha()
        model.value = "xyz"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + alpha()
        model.value = "11_-"
        assert(!model.isValid)
    }
}

class AlphaNumericValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + alphaNumeric()
        model.value = "x1"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + alphaNumeric()
        model.value = "x&"
        assert(!model.isValid)
    }
}

class NumericValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + numeric()
        model.value = "23"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + numeric()
        model.value = "x"
        assert(!model.isValid)
    }
}

class CreditCardValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + creditCard()
        model.value = "378734493671000"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + creditCard()
        model.value = "123"
        assert(!model.isValid)
    }
}

class AllValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + all(prefix("p"), suffix("s"))
        model.value = "plus"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + all(prefix("p"), suffix("s"))
        model.value = "x"
        assert(!model.isValid)
    }
}

class AnyValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + any(prefix("p"), suffix("s"))
        model.value = "plus2"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + any(prefix("p"), suffix("s"))
        model.value = "x"
        assert(!model.isValid)
    }
}

class PrefixValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + prefix("x")
        model.value = "x"
        assert(model.isValid)
    }

    @Test
    fun `should be valid ignoreCase = true`() {
        val model = form + prefix("x", true)
        model.value = "X"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + prefix("x")
        model.value = "y"
        assert(!model.isValid)
    }
}

class SuffixValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + suffix("x")
        model.value = "ex"
        assert(model.isValid)
    }

    @Test
    fun `should be valid ignoreCase = true`() {
        val model = form + suffix("x", true)
        model.value = "eX"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + suffix("x")
        model.value = "yy"
        assert(!model.isValid)
    }
}

class LongTextRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + textLength(1, 5)
        model.value = "ee"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + textLength(1, 5)
        model.value = "eeeeeeee"
        assert(!model.isValid)
    }
}

class FloatRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + floatRange(1f, 5f)
        model.value = 1f
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + floatRange(1f, 5f)
        model.value = 7f
        assert(!model.isValid)
    }
}

class DoubleRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + doubleRange(1.0, 5.0)
        model.value = 2.0
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + doubleRange(1.0, 5.0)
        model.value = 7.0
        assert(!model.isValid)
    }
}

class LongRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + longRange(1, 5)
        model.value = 1
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + longRange(1, 5)
        model.value = 7
        assert(!model.isValid)
    }
}

class IntRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + intRange(1, 5)
        model.value = 2
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + intRange(1, 5)
        model.value = 8
        assert(!model.isValid)
    }
}

class CharRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + charRange('a', 'c')
        model.value = 'b'
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + charRange('a', 'c')
        model.value = 'x'
        assert(!model.isValid)
    }
}

class ShortRangeValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + shortRange(1, 5)
        model.value = 1
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + shortRange(1, 5)
        model.value = 8
        assert(!model.isValid)
    }
}

class ByteValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + byteRange(1, 5)
        model.value = 1
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + byteRange(1, 5)
        model.value = 7
        assert(!model.isValid)
    }
}

class BooleanValidationTest {
    private lateinit var form: Form

    @Before
    fun setup() {
        form = Form.create {}
    }

    @Test
    fun `should be valid`() {
        val model = form + boolean(true)
        model.value = true
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = form + boolean(true)
        model.value = false
        assert(!model.isValid)
    }
}