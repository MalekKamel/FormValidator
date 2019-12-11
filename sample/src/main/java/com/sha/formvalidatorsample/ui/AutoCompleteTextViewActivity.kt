package com.sha.formvalidatorsample.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast

import com.sha.formvalidator.util.ValidatorFactory
import com.sha.formvalidator.validator.CreditCardValidator
import com.sha.formvalidator.validator.pattern.EmailValidator
import com.sha.formvalidator.widget.FormAutoCompleteTextView
import com.sha.formvalidator.widget.FormEditText
import com.sha.formvalidatorsample.R

class AutoCompleteTextViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete)
    }

    fun onClickValidate(v: View) {
        val autoCompleteTv = findViewById<FormAutoCompleteTextView>(R.id.autoCompleteTv)
        if (autoCompleteTv.validate()) {
            Toast.makeText(this, "Valid", Toast.LENGTH_LONG).show()
        }
    }
}
