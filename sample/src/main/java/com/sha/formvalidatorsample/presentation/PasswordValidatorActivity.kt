package com.sha.formvalidatorsample.presentation

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.sha.formvalidator.textview.ValidatorFactory
import com.sha.formvalidator.widget.FormEditText
import com.sha.formvalidatorsample.R

class PasswordValidatorActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        val tvDescription = findViewById<TextView>(R.id.tv_description)
        val tvTitle = findViewById<TextView>(R.id.tv_title)

        tvDescription.setText(R.string.password_description)
        tvTitle.setText(R.string.passwords_match)

        //Interesting stuff starts here

        val etPassword = findViewById<FormEditText>(R.id.etPassword)
        val etConfirmPassword = findViewById<FormEditText>(R.id.etConfirmPassword)

        etPassword.addValidator(ValidatorFactory.passwordMatch(
                "Passwords don't match!",
                etPassword,
                etConfirmPassword
        ))
    }

    fun onClickValidate(v: View) {
        val fdt = findViewById<FormEditText>(R.id.etPassword)
        if (!fdt.validate()) return
        Toast.makeText(this, "Valid", Toast.LENGTH_LONG).show()
    }
}
