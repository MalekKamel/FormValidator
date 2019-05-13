package com.sha.formvalidatorsample.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.sha.formvalidator.FormValidator;
import com.sha.formvalidator.RxFormValidator;
import com.sha.formvalidator.widget.FormEditText;
import com.sha.formvalidatorsample.R;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RxFormActivity extends Activity {

    private FormEditText etAllowEmpty;
    private FormEditText etAlpha;
    private FormEditText etPhone;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

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
        Disposable disposable =  new RxFormValidator<>(etAllowEmpty, etAlpha, etPhone)
                .validateOnClick(findViewById(R.id.btnValidateOnClick))
                .subscribe(isValid -> toast("Validate on Click result: " + isValid));

        compositeDisposable.add(disposable);
    }

    private void justCheck() {
        findViewById(R.id.btnJustCheck).setOnClickListener(__ -> {

            Disposable disposable = new RxFormValidator<>(etAllowEmpty, etAlpha, etPhone)
                    .validate()
                    .subscribe(isValid -> toast("Just check result: " + isValid));

            compositeDisposable.add(disposable);
        });
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null)
            compositeDisposable.dispose();
    }
}
