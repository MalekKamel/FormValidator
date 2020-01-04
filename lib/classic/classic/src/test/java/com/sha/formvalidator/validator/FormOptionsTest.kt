package com.sha.formvalidator.validator

import com.sha.formvalidator.model.FormOptions
import com.sha.formvalidator.model.IgnoreField
import org.junit.Test

class FormOptionsTest {

    @Test
    fun dsl_validationInterceptor() {
      val options = FormOptions.create { validationInterceptor = { IgnoreField.YES } }
        assert(options.validationInterceptor != null)
    }

    @Test
    fun dsl_ignoreFieldsIds() {
        val options = FormOptions.create { ignoreFieldsIds = listOf(1, 2) }
        assert(options.ignoreFieldsIds.size == 2)
    }

    @Test
    fun dsl_ignoreHiddenFields() {
        val options = FormOptions.create { ignoreHiddenFields = false }
        assert(!options.ignoreHiddenFields)
    }

    @Test
    fun builder_validationInterceptor() {
        val options = FormOptions.Builder()
                .validationInterceptor  { IgnoreField.YES }
                .build()
        assert(options.validationInterceptor != null)
    }

    @Test
    fun builder_ignoreFieldsIds() {
        val options = FormOptions.Builder()
                .ignoreFieldsIds(listOf(1, 2))
                .build()
        assert(options.ignoreFieldsIds.size == 2)
    }

    @Test
    fun builder_ignoreHiddenFields() {
        val options = FormOptions.Builder()
                .ignoreHiddenFields(false)
                .build()
        assert(!options.ignoreHiddenFields)
    }
}