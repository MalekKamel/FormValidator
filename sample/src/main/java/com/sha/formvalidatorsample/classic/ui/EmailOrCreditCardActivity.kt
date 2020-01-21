package com.sha.formvalidatorsample.classic.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast

import com.sha.formvalidator.Validators
import com.sha.formvalidator.any
import com.sha.formvalidator.core.validator.CreditCardValidator
import com.sha.formvalidator.core.validator.ErrorGenerator
import com.sha.formvalidator.core.validator.pattern.EmailValidator
import com.sha.formvalidator.widget.FormEditText
import com.sha.formvalidatorsample.R

class EmailOrCreditCardActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field)

        val flContainer = findViewById<FrameLayout>(R.id.fl)
        val tvDescription = findViewById<TextView>(R.id.tv_description)
        val tvTitle = findViewById<TextView>(R.id.tv_title)

        flContainer.addView(LayoutInflater.from(this).inflate(R.layout.field_email_or_creditcard, flContainer, false))
        tvDescription.setText(R.string.description_email_or_credit)
        tvTitle.setText(R.string.email_or_credit_title)

        //Interesting stuff starts here

        val et = findViewById<FormEditText>(R.id.et)

        et + any(CreditCardValidator(), EmailValidator()) {
            errorMessage = "This is neither a credit card or an email"
        }
    }

    fun onClickValidate(v: View) {
        val fdt = findViewById<FormEditText>(R.id.et)
        if (fdt.validate()) {
            Toast.makeText(this, "Valid", Toast.LENGTH_LONG).show()
        }
    }
}
