package com.sha.formvalidator.validator

import android.widget.TextView
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.sha.formvalidator.textview.validator.CreditCardValidator
import com.sha.formvalidator.textview.validator.PrefixValidator
import com.sha.formvalidator.textview.validator.TextViewValidator
import com.sha.formvalidator.textview.validator.composite.OrValidator
import org.junit.Before
import org.junit.Test

class OrValidatorTest {
    lateinit var validator: TextViewValidator
    lateinit var tv: TextView

    @Before
    fun setup() {
        tv = mock()
        validator = OrValidator(
                "Invalid!",
                CreditCardValidator("Invalid Card!"),
                PrefixValidator("3787", "Invalid Prefix!"))
    }

    @Test
    fun validate_valid() {
        given(tv.text).will { "378734493671000" }
        assert(validator.isValid(tv))
    }

    @Test
    fun validate_firstInvalid() {
        given(tv.text).will { "37873449367100099" }
        assert(validator.isValid(tv))
        assert(validator.errorMessage == "Invalid!")
    }

    @Test
    fun validate_secondInvalid() {
        given(tv.text).will { "6331101999990016" }
        assert(validator.isValid(tv))
        assert(validator.errorMessage == "Invalid!")
    }

    @Test
    fun validate_allInvalid() {
        given(tv.text).will { "63311019999900168" }
        assert(!validator.isValid(tv))
        assert(validator.errorMessage == "Invalid!")
    }
}