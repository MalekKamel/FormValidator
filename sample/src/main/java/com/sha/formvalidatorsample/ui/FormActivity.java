package com.sha.formvalidatorsample.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.sha.formvalidator.FormValidator;
import com.sha.formvalidator.widget.FormEditText;
import com.sha.formvalidatorsample.R;

public class FormActivity extends Activity {
    private FormEditText etAllowEmpty;
    private FormEditText etAlpha;
    private FormEditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        setupViews();
        validateOnClick();
        justCheck();
    }

    private void setupViews() {
        etAllowEmpty = findViewById(R.id.etAllowEmpty);
        etAlpha = findViewById(R.id.etAlpha);
        etPhone = findViewById(R.id.etPhone);
    }

    private void validateOnClick() {
        new FormValidator<>(etAllowEmpty, etAlpha, etPhone)
                .validateOnClick(
                        findViewById(R.id.btnValidateOnClick),
                        isValid -> toast("Validate on Click result: " + isValid)
                );

    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT)
                .show();
    }

    private void justCheck() {
        findViewById(R.id.btnJustCheck).setOnClickListener(__ -> {
            boolean isValid = new FormValidator<>(etAllowEmpty, etAlpha, etPhone).isValid();
            toast("Just check result: " + isValid);
        });
    }

}
