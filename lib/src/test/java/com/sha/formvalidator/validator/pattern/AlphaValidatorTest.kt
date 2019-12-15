package com.sha.formvalidator.validator.pattern

import android.widget.TextView
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.sha.formvalidator.textview.validator.CreditCardValidator
import com.sha.formvalidator.textview.validator.TextViewValidator
import com.sha.formvalidator.textview.validator.pattern.AlphaNumericValidator
import com.sha.formvalidator.textview.validator.pattern.AlphaValidator
import org.junit.Before
import org.junit.Test

class AlphaValidatorTest {
    lateinit var validator: TextViewValidator
    lateinit var tv: TextView

    @Before
    fun setup() {
        tv = mock()
        validator = AlphaValidator("Invalid!")
    }

    @Test
    fun validate_valid() {
        given(tv.text).will { "rrlll" }
        assert(validator.isValid(tv))
    }

    @Test
    fun validate_invalid() {
        given(tv.text).will { "11*%" }
        assert(!validator.isValid(tv))
    }
}