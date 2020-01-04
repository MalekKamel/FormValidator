package com.sha.formvalidatorsample.classic.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sha.formvalidator.widget.FormAutoCompleteTextView
import com.sha.formvalidatorsample.R

class AutoCompleteTextViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete)
    }

    fun onClickValidate(v: View) {
        val autoCompleteTv = findViewById<FormAutoCompleteTextView>(R.id.autoCompleteTv)
        if (autoCompleteTv.validate())
            Toast.makeText(this, "Valid", Toast.LENGTH_LONG).show()
    }
}
