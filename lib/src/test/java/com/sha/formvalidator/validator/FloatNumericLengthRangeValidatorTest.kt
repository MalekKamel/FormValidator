package com.sha.formvalidator.validator

import android.widget.TextView
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.sha.formvalidator.textview.validator.CreditCardValidator
import com.sha.formvalidator.textview.validator.DateValidator
import com.sha.formvalidator.textview.validator.FloatNumericRangeValidator
import com.sha.formvalidator.textview.validator.TextViewValidator
import org.junit.Before
import org.junit.Test

class FloatNumericLengthRangeValidatorTest {
    lateinit var validator: TextViewValidator
    lateinit var tv: TextView

    @Before
    fun setup() {
        tv = mock()
        validator = FloatNumericRangeValidator("Invalid!", 1.0, 5.0)
    }

    @Test
    fun validate_valid() {
        given(tv.text).will { "1" }
        assert(validator.isValid(tv))
    }

    @Test
    fun validate_invalid() {
        given(tv.text).will { "6" }
        assert(!validator.isValid(tv))
    }
}