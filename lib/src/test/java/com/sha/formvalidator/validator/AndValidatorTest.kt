package com.sha.formvalidator.validator

import android.widget.TextView
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.sha.formvalidator.textview.validator.*
import com.sha.formvalidator.textview.validator.composite.AndValidator
import org.junit.Before
import org.junit.Test

class AndValidatorTest {
    lateinit var validator: TextViewValidator
    lateinit var tv: TextView

    @Before
    fun setup() {
        tv = mock()
        validator = AndValidator(
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
        assert(!validator.isValid(tv))
        assert(validator.errorMessage == "Invalid Card!")
    }

    @Test
    fun validate_secondInvalid() {
        given(tv.text).will { "6331101999990016" }
        assert(!validator.isValid(tv))
        assert(validator.errorMessage == "Invalid Prefix!")
    }
}