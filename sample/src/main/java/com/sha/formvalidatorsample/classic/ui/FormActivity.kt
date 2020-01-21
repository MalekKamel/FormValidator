package com.sha.formvalidatorsample.classic.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sha.formvalidator.Form
import com.sha.formvalidator.model.FormOptions
import com.sha.formvalidator.model.IgnoreField
import com.sha.formvalidatorsample.R

class FormActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        findViewById<Form>(R.id.form).validateOnClick(findViewById<View>(R.id.btnValidateFormLayout)) {
            Toast.makeText(this, "Form result: $it", Toast.LENGTH_SHORT).show()
        }
        setupForm()
    }

    private fun setupForm() {
        findViewById<Form>(R.id.form).options = FormOptions.create {
            validationInterceptor = { field ->
                when(field.id) {
                    R.id.etIgnored -> IgnoreField.YES
                    else -> IgnoreField.NO
                }
            }
            ignoreFieldsIds = listOf(R.id.etIgnoredId)
            ignoreHiddenFields = true
            shakeOnError = true
        }
    }
}
