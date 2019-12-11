package com.sha.formvalidatorsample;

import android.app.Application;

import com.sha.formvalidator.util.Validators;
import com.sha.formvalidatorsample.validator.NumberOneCustomValidator;

import java.util.Arrays;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Validators.customValidators = Arrays.asList(
                new NumberOneCustomValidator("Value doesn't equal 1") // use context.getString() in production
        );
    }
}
