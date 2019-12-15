package com.sha.formvalidator

import android.view.ViewGroup

open class FormHelper {

    fun fields(viewGroup: ViewGroup): List<Validatable> {
        val children: MutableList<Validatable> = mutableListOf()

        for (i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)
            // the view group may be Validatable, if it's the case, we shouldn't
            // loop over its children
            if(child !is Validatable && child is ViewGroup) {
                // loop recursively to get all children
                children += fields(child)
                continue
            }
            (child as? Validatable)?.let { children += it }
        }

        return children
    }
}