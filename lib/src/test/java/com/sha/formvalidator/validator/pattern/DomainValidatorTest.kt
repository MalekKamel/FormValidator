package com.sha.formvalidator.validator.pattern

import android.widget.TextView
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.sha.formvalidator.textview.validator.CreditCardValidator
import com.sha.formvalidator.textview.validator.TextViewValidator
import com.sha.formvalidator.textview.validator.pattern.AlphaNumericValidator
import com.sha.formvalidator.textview.validator.pattern.AlphaValidator
import com.sha.formvalidator.textview.validator.pattern.DomainValidator
import org.junit.Before
import org.junit.Test

class DomainValidatorTest {
    lateinit var validator: TextViewValidator
    lateinit var tv: TextView

    @Before
    fun setup() {
        tv = mock()
        validator = DomainValidator("Invalid!")
    }

    @Test
    fun validate_valid() {
        given(tv.text).will { "https://www.google.com" }
        assert(validator.isValid(tv))
    }

    @Test
    fun validate_invalid() {
        given(tv.text).will { "11*%" }
        assert(!validator.isValid(tv))
    }
}