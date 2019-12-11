package com.sha.formvalidatorsample.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.sha.formvalidator.FormValidator
import com.sha.formvalidator.widget.FormEditText
import com.sha.formvalidatorsample.R

class FormActivity : Activity() {
    private lateinit var etAllowEmpty: FormEditText
    private lateinit var etAlpha: FormEditText
    private lateinit var etPhone: FormEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        setupViews()
        validateOnClick()
        justCheck()
    }

    private fun setupViews() {
        etAllowEmpty = findViewById(R.id.etAllowEmpty)
        etAlpha = findViewById(R.id.etAlpha)
        etPhone = findViewById(R.id.etPhone)
    }

    private fun validateOnClick() {
        FormValidator(etAllowEmpty, etAlpha, etPhone)
                .validateOnClick(findViewById<View>(R.id.btnValidateOnClick)) {
                    toast("Validate on Click result: $it")
                }

    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT)
                .show()
    }

    private fun justCheck() {
        findViewById<View>(R.id.btnJustCheck).setOnClickListener {
            val isValid = FormValidator(etAllowEmpty, etAlpha, etPhone).isValid
            toast("Just check result: $isValid")
        }
    }

}
