package com.sha.formvalidator

import android.view.View
import android.view.ViewGroup
import com.sha.formvalidator.model.FormOptions
import com.sha.formvalidator.model.IgnoreField

open class FormHelper {

    fun fields(viewGroup: ViewGroup, options: FormOptions): List<Validatable> {
        val children: MutableList<Validatable> = mutableListOf()

        for (i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)

            // the view group may be Validatable, if it's the case, we shouldn't
            // loop over its children
            if(child !is Validatable && child is ViewGroup) {
                // loop recursively to get all children
                children += fields(child, options)
                continue
            }

            // add only Validatable
            if (child !is Validatable) continue

            // don't validate hidden fields, if it's desired by client
            if (options.ignoreHiddenFields && child.visibility != View.VISIBLE) continue

            // ignore ID
            if(options.ignoreFieldsIds.any { it == child.id }) continue

            // apply interceptor
            if(options.validationInterceptor?.invoke(child) == IgnoreField.YES) continue

            children += child
        }

        return children
    }
}