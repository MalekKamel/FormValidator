package com.sha.formvalidator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout


open class Form: LinearLayout {

    constructor(context: Context) : super(context) { setup(null) }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs) }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) { setup(attrs) }

    private fun setup(attrs: AttributeSet?) {

    }

    open fun formChildren(viewGroup: ViewGroup): List<Validatable> {
        val children: MutableList<Validatable> = mutableListOf()

        for (i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)
            // the view group may be Validatable, if it's the case, we shouldn't
            // loop over its children
            if(child !is Validatable && child is ViewGroup){
                // loop recursively to get all children
                children += formChildren(child)
                continue
            }
            (child as? Validatable)?.let { children += it }
        }

        return children
    }

    open fun validate(): Boolean {
        var isValid = true
        formChildren(this).forEach { isValid = it.validate() && isValid }
        return isValid
    }

   open fun validateOnClick(view: View, validationCallback: (Boolean) -> Unit) {
        view.setOnClickListener { validationCallback(validate()) }
    }

}