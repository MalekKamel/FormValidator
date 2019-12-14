package com.sha.formvalidator.validator

import android.widget.TextView
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.sha.formvalidator.textview.validator.CreditCardValidator
import com.sha.formvalidator.textview.validator.DateValidator
import com.sha.formvalidator.textview.validator.TextViewValidator
import org.junit.Before
import org.junit.Test

class DateValidatorTest {
    lateinit var validator: TextViewValidator
    lateinit var tv: TextView

    @Before
    fun setup() {
        tv = mock()
        validator = DateValidator("Invalid!", "YYYY:MM:DD")
    }

    @Test
    fun validate_valid() {
        given(tv.text).will { "2019:12:14" }
        assert(validator.isValid(tv))
    }

    @Test
    fun validate_invalidIfWrong() {
        given(tv.text).will { "2019" }
        assert(!validator.isValid(tv))
    }

    @Test
    fun validate_invalidIfEmpty() {
        given(tv.text).will { "" }
        assert(!validator.isValid(tv))
    }
}