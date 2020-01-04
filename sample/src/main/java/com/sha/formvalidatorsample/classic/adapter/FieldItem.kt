package com.sha.formvalidatorsample.classic.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent

import com.sha.formvalidatorsample.classic.ui.FieldActivity

class FieldItem {
    private lateinit var clazz: Class<out Activity>
    var layoutRes = -1
    var description: Int = 0
    var title: String

    constructor(title: String, _clazz: Class<out Activity>) {
        this.title = title
        clazz = _clazz
    }

    constructor(title: String, layoutRes: Int, description: Int) {
        this.title = title
        this.layoutRes = layoutRes
        this.description = description
    }

    fun showDemo(ctx: Context) {
        if (layoutRes != -1) {
            ctx.startActivity(FieldActivity.buildIntent(ctx, title, layoutRes, description))
            return
        }
        ctx.startActivity(Intent(ctx, clazz))
    }

}
