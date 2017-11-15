package com.sha.kamel.formvalidator;


import android.text.TextUtils;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Sha on 11/8/17.
 */

public abstract class Validator {
    protected EditText et;
    private String error;

    protected abstract boolean validate(String s);

    private String initialValue;
    private ValidationOptions options;
    public Validator(EditText et) {
        this.et = et;
    }

    public final Observable<ValidationBean> start(ValidationOptions options){
        this.options = options;

        return RxTextView.textChanges(et)
                .skip(et.getText().toString().isEmpty() ? 1 : 0) // Check is necessary to enable initial value
                .debounce(200, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .observeOn(AndroidSchedulers.mainThread())
                .map(s -> !isEmpty(s)) // If empty, stop. We don't want to add others until this succeed
                .map(isValid -> {
                    if (isValid) // If empty, stop. We don't want to add others until this succeed.
                        return validate(et.getText().toString());
                    return isValid;
                })
                .map(isValid -> new ValidationBean(et, isValid, this));
    }

    private boolean isEmpty(String s) {
        boolean isEmpty = s.trim().isEmpty();
        if (isEmpty)
            error(options.emptyMessage());
        return isEmpty;
    }


    void emitInitialValue() {
        if (!TextUtils.isEmpty(initialValue)){
            et.setText(initialValue);
        }
    }

    public Validator initialValue(String value){
        this.initialValue = value;
        return this;
    }

    protected void error(String error){
        this.error = error;
        if (options.shouldValidateOnChange)
            et.setError(error);
    }

    public String getError() {
        return TextUtils.isEmpty(error) ? options.emptyMessage() : error;
    }
}
