package com.sha.formvalidator.model.helper

import android.view.View
import android.view.animation.AnimationUtils
import com.sha.formvalidator.R

internal object AnimationHelper {

    fun error(v: View) {
        v.startAnimation(
                AnimationUtils.loadAnimation(
                        v.context,
                        R.anim.shake_error)
        )
    }
}