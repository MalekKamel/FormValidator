package com.sha.formvalidator.validator

import android.widget.TextView
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.sha.formvalidator.textview.validator.CreditCardValidator
import com.sha.formvalidator.textview.validator.DateValidator
import com.sha.formvalidator.textview.validator.DummyValidator
import com.sha.formvalidator.textview.validator.TextViewValidator
import org.junit.Before
import org.junit.Test

class DummyValidatorTest {
    lateinit var validator: TextViewValidator
    lateinit var tv: TextView

    @Before
    fun setup() {
        tv = mock()
        validator = DummyValidator()
    }

    @Test
    fun validate_valid() {
        given(tv.text).will { "kkkkk" }
        assert(validator.isValid(tv))
    }

    @Test
    fun validate_validIfEmpty() {
        given(tv.text).will { "" }
        assert(validator.isValid(tv))
    }
}