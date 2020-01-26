package com.sha.formvalidator

import org.junit.Test

class MandatoryValidationTest {

    @Test
    fun `should be valid`() {
        val model = mandatory<String>()
        model.value = "x"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = mandatory<String>()
        model.value = ""
        assert(!model.isValid)
    }
}

class OptionalValidationTest {

    @Test
    fun `should be valid if true`() {
        val model = optional<String>()
        model.value = "x"
        assert(model.isValid)
    }

    @Test
    fun `should be valid if false`() {
        val model = optional<String>()
        model.value = ""
        assert(model.isValid)
    }
}

class PersonNameValidationTest {

    @Test
    fun `should be valid`() {
        val model = personName()
        model.value = "Shaban"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = personName()
        model.value = "sha21"
        assert(!model.isValid)
    }
}

class PersonFullNameValidationTest {

    @Test
    fun `should be valid`() {
        val model = personFullName()
        model.value = "Shaban Kamel"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = personFullName()
        model.value = "dd33"
        assert(!model.isValid)
    }
}

class DomainValidationTest {

    @Test
    fun `should be valid`() {
        val model = domain()
        model.value = "google.com"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = domain()
        model.value = "x"
        assert(!model.isValid)
    }
}

class AlphaValidationTest {

    @Test
    fun `should be valid`() {
        val model = alpha()
        model.value = "xyz"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = alpha()
        model.value = "11_-"
        assert(!model.isValid)
    }
}

class AlphaNumericValidationTest {

    @Test
    fun `should be valid`() {
        val model = alphaNumeric()
        model.value = "x1"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = alphaNumeric()
        model.value = "x&"
        assert(!model.isValid)
    }
}

class NumericValidationTest {

    @Test
    fun `should be valid`() {
        val model = numeric()
        model.value = "23"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = numeric()
        model.value = "x"
        assert(!model.isValid)
    }
}

class CreditCardValidationTest {

    @Test
    fun `should be valid`() {
        val model = creditCard()
        model.value = "378734493671000"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = creditCard()
        model.value = "123"
        assert(!model.isValid)
    }
}

class AllValidationTest {

    @Test
    fun `should be valid`() {
        val model = all(prefix("p"), suffix("s"))
        model.value = "plus"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = all(prefix("p"), suffix("s"))
        model.value = "x"
        assert(!model.isValid)
    }
}

class AnyValidationTest {

    @Test
    fun `should be valid`() {
        val model = any(prefix("p"), suffix("s"))
        model.value = "plus2"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = any(prefix("p"), suffix("s"))
        model.value = "x"
        assert(!model.isValid)
    }
}

class PrefixValidationTest {

    @Test
    fun `should be valid`() {
        val model = prefix("x")
        model.value = "x"
        assert(model.isValid)
    }

    @Test
    fun `should be valid ignoreCase = true`() {
        val model = prefix("x", true)
        model.value = "X"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = prefix("x")
        model.value = "y"
        assert(!model.isValid)
    }
}

class SuffixValidationTest {

    @Test
    fun `should be valid`() {
        val model = suffix("x")
        model.value = "ex"
        assert(model.isValid)
    }

    @Test
    fun `should be valid ignoreCase = true`() {
        val model = suffix("x", true)
        model.value = "eX"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = suffix("x")
        model.value = "yy"
        assert(!model.isValid)
    }
}

class LongTextRangeValidationTest {

    @Test
    fun `should be valid`() {
        val model = textLength(1, 5)
        model.value = "ee"
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = textLength(1, 5)
        model.value = "eeeeeeee"
        assert(!model.isValid)
    }
}

class FloatRangeValidationTest {

    @Test
    fun `should be valid`() {
        val model = floatRange(1f, 5f)
        model.value = 1f
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = floatRange(1f, 5f)
        model.value = 7f
        assert(!model.isValid)
    }
}

class DoubleRangeValidationTest {

    @Test
    fun `should be valid`() {
        val model = doubleRange(1.0, 5.0)
        model.value = 2.0
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = doubleRange(1.0, 5.0)
        model.value = 7.0
        assert(!model.isValid)
    }
}

class LongRangeValidationTest {

    @Test
    fun `should be valid`() {
        val model = longRange(1, 5)
        model.value = 1
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = longRange(1, 5)
        model.value = 7
        assert(!model.isValid)
    }
}

class IntRangeValidationTest {

    @Test
    fun `should be valid`() {
        val model = intRange(1, 5)
        model.value = 2
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = intRange(1, 5)
        model.value = 8
        assert(!model.isValid)
    }
}

class CharRangeValidationTest {

    @Test
    fun `should be valid`() {
        val model = charRange('a', 'c')
        model.value = 'b'
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = charRange('a', 'c')
        model.value = 'x'
        assert(!model.isValid)
    }
}

class ShortRangeValidationTest {

    @Test
    fun `should be valid`() {
        val model = shortRange(1, 5)
        model.value = 1
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = shortRange(1, 5)
        model.value = 8
        assert(!model.isValid)
    }
}

class ByteValidationTest {

    @Test
    fun `should be valid`() {
        val model = byteRange(1, 5)
        model.value = 1
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = byteRange(1, 5)
        model.value = 7
        assert(!model.isValid)
    }
}

class ConditionValidationTest {

    @Test
    fun `should be valid`() {
        val model = condition({ true })
        model.value = true
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = condition({ true })
        model.value = false
        assert(!model.isValid)
    }
}

class BooleanValidationTest {

    @Test
    fun `should be valid`() {
        val model = boolean(true)
        model.value = true
        assert(model.isValid)
    }

    @Test
    fun `should be invalid`() {
        val model = boolean(true)
        model.value = false
        assert(!model.isValid)
    }
}