package com.sha.formvalidator.core

import com.sha.formvalidator.core.validator.*
import org.junit.Before
import org.junit.Test

class LengthRangeValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        validator = LengthRangeTextValidator(1, 5)
    }

    @Test
    fun validate_valid() {
        validator.value = "1"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalid() {
        validator.value = "123456"
        assert(!validator.isValid)
    }
}

class WrapTextValidatorTest {
    lateinit var validator: TextValidator

    @Before
    fun setup() {
        val v = FloatRangeValidator(
                1f,
                5f
        )
        validator = WrapTextValidator(v) { it.toFloat() }
    }

    @Test
    fun validate_valid() {
        validator.value = "1"
        assert(validator.isValid)
    }

    @Test
    fun validate_invalid() {
        validator.value = "6"
        assert(!validator.isValid)
    }
}

class DoubleRangeValidatorTest {
    lateinit var validator: DoubleRangeValidator

    @Before
    fun setup() {
        validator = DoubleRangeValidator(1.0, 5.0)
    }

    @Test
    fun validRange() {
        validator.value = 1.0
        assert(validator.isValid)
    }

    @Test
    fun invalidRange() {
        validator.value = 6.0
        assert(!validator.isValid)
    }
}

class FloatRangeValidatorTest {
    lateinit var validator: FloatRangeValidator

    @Before
    fun setup() {
        validator = FloatRangeValidator(1.0f, 5.0f)
    }

    @Test
    fun validRange() {
        validator.value = 1.0f
        assert(validator.isValid)
    }

    @Test
    fun invalidRange() {
        validator.value = 6.0f
        assert(!validator.isValid)
    }
}

class LongRangeValidatorTest {
    lateinit var validator: LongRangeValidator

    @Before
    fun setup() {
        validator = LongRangeValidator(1, 5)
    }

    @Test
    fun validRange() {
        validator.value = 1
        assert(validator.isValid)
    }

    @Test
    fun invalidRange() {
        validator.value = 6
        assert(!validator.isValid)
    }
}

class IntRangeValidatorTest {
    lateinit var validator: IntRangeValidator

    @Before
    fun setup() {
        validator = IntRangeValidator(1, 5)
    }

    @Test
    fun validRange() {
        validator.value = 1
        assert(validator.isValid)
    }

    @Test
    fun invalidRange() {
        validator.value = 6
        assert(!validator.isValid)
    }
}

class CharRangeValidatorTest {
    lateinit var validator: CharRangeValidator

    @Before
    fun setup() {
        validator = CharRangeValidator('a', 'c')
    }

    @Test
    fun validRange() {
        validator.value = 'b'
        assert(validator.isValid)
    }

    @Test
    fun invalidRange() {
        validator.value = 'x'
        assert(!validator.isValid)
    }
}

class ShortRangeValidatorTest {
    lateinit var validator: ShortRangeValidator

    @Before
    fun setup() {
        validator = ShortRangeValidator(1, 5)
    }

    @Test
    fun validRange() {
        validator.value = 1
        assert(validator.isValid)
    }

    @Test
    fun invalidRange() {
        validator.value = 6
        assert(!validator.isValid)
    }
}

class ByteRangeValidatorTest {
    lateinit var validator: ByteRangeValidator

    @Before
    fun setup() {
        validator = ByteRangeValidator(1, 5)
    }

    @Test
    fun validRange() {
        validator.value = 1
        assert(validator.isValid)
    }

    @Test
    fun invalidRange() {
        validator.value = 6
        assert(!validator.isValid)
    }
}