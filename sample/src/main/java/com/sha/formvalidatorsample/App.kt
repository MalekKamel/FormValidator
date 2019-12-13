package com.sha.formvalidatorsample

import android.app.Application

import com.sha.formvalidator.textview.Validators
import com.sha.formvalidatorsample.validator.NumberOneCustomValidator

import java.util.Arrays

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Validators.customValidators = Arrays.asList(
                NumberOneCustomValidator("Value doesn't equal 1") // use context.getString() in production
        )
    }
}
