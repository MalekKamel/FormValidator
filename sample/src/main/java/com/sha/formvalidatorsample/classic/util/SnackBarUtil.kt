package com.sha.formvalidatorsample.classic.util

import android.os.Handler
import android.view.View
import android.widget.TextView

import com.google.android.material.snackbar.Snackbar
import com.sha.formvalidatorsample.R

object SnackBarUtil {

    fun gotIt(view: View, text: String) {
        Handler().postDelayed({
            multilineSnackbar(
                    Snackbar.make(
                            view, text, Snackbar.LENGTH_INDEFINITE)
                            .setAction("Got it") {

                            }
            ).show()
        }, 200)
    }

    private fun multilineSnackbar(snackbar: Snackbar): Snackbar {
        val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
        textView.maxLines = 5
        return snackbar
    }
}
