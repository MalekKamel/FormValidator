package com.sha.formvalidatorsample.presentation

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sha.formvalidator.FormValidator
import com.sha.formvalidatorsample.R
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        form.validateOnClick(btnValidateFormLayout) {
            Toast.makeText(this, "Form result: $it", Toast.LENGTH_SHORT).show()
        }

    }


    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }


}
