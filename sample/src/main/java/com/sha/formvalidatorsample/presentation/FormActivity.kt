package com.sha.formvalidatorsample.presentation

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.sha.formvalidator.model.FormOptions
import com.sha.formvalidator.model.IgnoreField
import com.sha.formvalidatorsample.R
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        form.validateOnClick(btnValidateFormLayout) {
            Toast.makeText(this, "Form result: $it", Toast.LENGTH_SHORT).show()
        }
        setupForm()
    }

    private fun setupForm() {
        form.options = FormOptions.create {
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
