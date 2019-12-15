package com.sha.formvalidator.model

import android.view.View

enum class IgnoreField {
    YES, NO
}
data class FormOptions(
        var validationInterceptor: ((View) -> IgnoreField)? = null,
        var ignoreFieldsIds: List<Int> = emptyList(),
        var ignoreHiddenFields: Boolean = true
){

    class Builder {
        private val options = FormOptions()

        fun validationInterceptor(interceptor: (((View) -> IgnoreField)?)): Builder {
            options.validationInterceptor = interceptor
            return this
        }

        fun ignoreFieldsIds(ids: List<Int>): Builder {
            options.ignoreFieldsIds = ids
            return this
        }

        fun ignoreHiddenFields(ignore: Boolean): Builder {
            options.ignoreHiddenFields = ignore
            return this
        }

        fun build(): FormOptions {
            return options
        }
    }

    companion object {
        fun defaultOptions(): FormOptions = Builder().build()

        fun create(block: FormOptions.() -> Unit): FormOptions{
            val options = FormOptions().apply { block() }
            return options
        }
    }
}

