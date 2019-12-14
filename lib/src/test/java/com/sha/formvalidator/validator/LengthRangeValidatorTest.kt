package com.sha.formvalidator.validator

import android.widget.TextView
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.sha.formvalidator.textview.validator.*
import org.junit.Before
import org.junit.Test

class LengthRangeValidatorTest {
    lateinit var validator: TextViewValidator
    lateinit var tv: TextView

    @Before
    fun setup() {
        tv = mock()
        validator = LengthRangeValidator("Invalid!", 1, 5)
    }

    @Test
    fun validate_valid() {
        given(tv.text).will { "1" }
        assert(validator.isValid(tv))
    }

    @Test
    fun validate_invalid() {
        given(tv.text).will { "123456" }
        assert(!validator.isValid(tv))
    }
}