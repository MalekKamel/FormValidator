package com.sha.formvalidatorsample.presentation

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.sha.formvalidatorsample.R
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_form.*

class RxFormActivity : Activity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        form.validateOnClick(btnValidateFormLayout) {
            Toast.makeText(this, "Form result: $it", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
