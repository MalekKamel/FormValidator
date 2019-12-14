package com.sha.formvalidator.validator

import android.widget.TextView
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.sha.formvalidator.textview.validator.*
import org.junit.Before
import org.junit.Test

class NumericValidatorTest {
    lateinit var validator: TextViewValidator
    lateinit var tv: TextView

    @Before
    fun setup() {
        tv = mock()
        validator = NumericValidator("Invalid!")
    }

    @Test
    fun validate_valid() {
        given(tv.text).will { "1" }
        assert(validator.isValid(tv))
    }

    @Test
    fun validate_invalidIfWrong() {
        given(tv.text).will { "6f" }
        assert(!validator.isValid(tv))
    }

    @Test
    fun validate_invalidIfEmpty() {
        given(tv.text).will { "" }
        assert(!validator.isValid(tv))
    }
}