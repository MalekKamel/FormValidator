package com.sha.formvalidatorsample.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast

import com.sha.formvalidator.util.ValidatorFactory
import com.sha.formvalidator.validator.PrefixValidator
import com.sha.formvalidator.validator.RangeValidator
import com.sha.formvalidator.widget.FormEditText
import com.sha.formvalidatorsample.R

class PrefixAndRangeValidatorActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field)

        setupUi()

        //Interesting stuff starts here

        val fdt = findViewById<FormEditText>(R.id.et)

        fdt.addValidator(
                ValidatorFactory.allValid(
                        PrefixValidator("d", "Must start with d."),
                        RangeValidator("Must be of length 1-5.", 1, 5)
                )
        )

    }

    private fun setupUi() {
        val flContainer = findViewById<FrameLayout>(R.id.fl)
        val tvDescription = findViewById<TextView>(R.id.tv_description)
        val tvTitle = findViewById<TextView>(R.id.tv_title)

        flContainer.addView(LayoutInflater.from(this).inflate(R.layout.field_email_or_creditcard, flContainer, false))
        tvDescription.setText(R.string.description_email_or_credit)
        tvTitle.setText(R.string.email_or_credit_title)

    }

    fun onClickValidate(v: View) {
        val fdt = findViewById<FormEditText>(R.id.et)
        if (fdt.validate()) {
            Toast.makeText(this, "Valid", Toast.LENGTH_LONG).show()
        }
    }
}
