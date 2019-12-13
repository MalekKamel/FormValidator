package com.sha.formvalidatorsample.presentation

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sha.formvalidator.RxFormValidator
import com.sha.formvalidator.widget.FormEditText
import com.sha.formvalidatorsample.R
import io.reactivex.disposables.CompositeDisposable

class RxFormActivity : Activity() {

    private lateinit var etAllowEmpty: FormEditText
    private lateinit var etAlpha: FormEditText
    private lateinit var etPhone: FormEditText
    private val compositeDisposable = CompositeDisposable()

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
        val disposable = RxFormValidator(etAllowEmpty, etAlpha, etPhone)
                .validateOnClick(findViewById<View>(R.id.btnValidateOnClick))
                .subscribe { isValid -> toast("Validate on Click result: " + isValid!!) }

        compositeDisposable.add(disposable)
    }

    private fun justCheck() {
        findViewById<View>(R.id.btnJustCheck).setOnClickListener {

            val disposable = RxFormValidator(etAllowEmpty, etAlpha, etPhone)
                    .validate()
                    .subscribe { isValid -> toast("Just check result: $isValid") }

            compositeDisposable.add(disposable)
        }
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT)
                .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
