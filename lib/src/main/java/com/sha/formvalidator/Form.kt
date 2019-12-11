package com.sha.formvalidator

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout


class Form: LinearLayout {

    constructor(context: Context) : super(context) {
        setup(null, context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup(attrs, context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs, context)
    }

    private fun setup(attrs: AttributeSet?, context: Context) {

    }

    private fun formChildren(viewGroup: ViewGroup): List<Validatable> {
        val children: MutableList<Validatable> = mutableListOf()

        for (i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)
            if(child is ViewGroup){
                // loop recursively to get all children
                children += formChildren(child)
                continue
            }
            (child as? Validatable)?.let { children += it }
        }

        return children
    }

    fun isValid(): Boolean {
        var isValid = true
        formChildren(this).forEach { isValid = it.validate() && isValid }
        return isValid
    }

    fun validate() = isValid()
}