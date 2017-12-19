package com.sha.kamel.formvalidator;


import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.sha.kamel.formvalidator.util.Callback;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Sha on 11/8/17.
 */

public abstract class Validator {
    TextInputLayout til;

    protected EditText et;
    private String error;

    protected abstract boolean validate(String s);

    private String initialValue;
    private ValidationOptions options;

    private Callback<Boolean> isEmptyListener;
    private boolean isErrorDisplayedOnSubmit;

    private Callback<Boolean> isErrorDisplayedOnSubmitCallback;

    public Validator(EditText et) {
        this.et = et;
    }

    public Validator(EditText et, TextInputLayout til) {
        this.et = et;
        this.til = til;
    }

    public final Observable<ValidationBean> start(ValidationOptions options){
        this.options = options;

        return RxTextView.textChanges(et)
                .subscribeOn(AndroidSchedulers.mainThread())
                .skip(options.shouldValidateOnChange ? 1 : 0)
                .debounce(200, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s -> {
                    if (isErrorDisplayedOnSubmit){
                        isErrorDisplayedOnSubmit = false;
                        if (isErrorDisplayedOnSubmitCallback != null)
                            isErrorDisplayedOnSubmitCallback.call(false);
                    }
                })
                .doOnNext(s -> {
                    if (isEmptyListener != null){
                        isEmptyListener.call(s.isEmpty());
                    }
                })
                .doOnNext(s -> clearError())
                .map(s -> !isEmpty(s)) // If empty, stop. We don't want to add others until this succeed
                .map(isValid -> {
                    if (isValid) // If empty, stop. We don't want to add others until this succeed.
                        return validate(et.getText().toString());
                    return isValid;
                })
                .map(isValid -> new ValidationBean(et, til, isValid, this));
    }

    private void clearError() {
        if (til != null) til.setError(null);
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
        if (!TextUtils.isEmpty(initialValue)){
            et.setText(initialValue);
        }
        this.initialValue = value;
        return this;
    }

    public Validator isEmptyListener(Callback<Boolean> callback){
        this.isEmptyListener = callback;
        return this;
    }

    public boolean isErrorDisplayedOnSubmit(){
        return isErrorDisplayedOnSubmit;
    }

    public Validator isErrorDisplayedOnSubmit(Callback<Boolean> callback){
        isErrorDisplayedOnSubmitCallback = callback;
        return this;
    }

    public Validator setErrorDisplayedOnSubmit(boolean isDisplayed){
        isErrorDisplayedOnSubmit = isDisplayed;

        if (isErrorDisplayedOnSubmitCallback != null){
            isErrorDisplayedOnSubmitCallback.call(isDisplayed);
        }

        return this;
    }

    public void notifyEmpty(){
        if (isEmptyListener != null)
            isEmptyListener.call(et.getText().toString().isEmpty());
    }

    protected void error(String error){
        this.error = error;
        if (options.shouldValidateOnChange){
            if (til == null)
                et.setError(error);
            else
                til.setError(error);
        }
    }

    public String getError() {
        return TextUtils.isEmpty(error) ? options.emptyMessage() : error;
    }


}
