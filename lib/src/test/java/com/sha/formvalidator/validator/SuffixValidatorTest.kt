package com.sha.formvalidator.validator

import android.widget.TextView
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.sha.formvalidator.textview.validator.PrefixValidator
import com.sha.formvalidator.textview.validator.SuffixValidator
import com.sha.formvalidator.textview.validator.TextViewValidator
import org.junit.Before
import org.junit.Test

class SuffixValidatorTest {
    lateinit var validator: TextViewValidator
    lateinit var tv: TextView

    @Before
    fun setup() {
        tv = mock()
        validator = SuffixValidator("suffix", "Invalid!")
    }

    @Test
    fun validate_valid() {
        given(tv.text).will { "xxsuffix" }
        assert(validator.isValid(tv))
    }

    @Test
    fun validate_invalid() {
        given(tv.text).will { "11" }
        assert(!validator.isValid(tv))
    }
}