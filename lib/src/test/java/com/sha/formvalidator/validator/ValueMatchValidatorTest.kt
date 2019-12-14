package com.sha.formvalidator.validator

import android.widget.TextView
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.sha.formvalidator.textview.validator.CreditCardValidator
import com.sha.formvalidator.textview.validator.TextViewValidator
import com.sha.formvalidator.textview.validator.ValueMatchValidator
import org.junit.Before
import org.junit.Test

class ValueMatchValidatorTest {
    lateinit var validator: TextViewValidator
    lateinit var tv: TextView

    @Before
    fun setup() {
        tv = mock()
    }

    @Test
    fun validate_valid() {
        val other: TextView = mock()
        given(tv.text).will { "378734493671000" }
        given(other.text).will { "378734493671000" }
        validator = ValueMatchValidator("Invalid!", other)
        assert(validator.isValid(tv))
    }

    @Test
    fun validate_invalid() {
        val other: TextView = mock()
        given(tv.text).will { "378734493671000" }
        given(other.text).will { "11" }
        validator = ValueMatchValidator("Invalid!", other)
        assert(!validator.isValid(tv))
    }
}