package com.sha.kamel.formvalidator;


import android.support.design.widget.TextInputLayout;
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
    protected String errorMessage;

    protected abstract boolean validate(String s);

    private String initialValue;
    protected ValidationOptions options;

    private Callback<Boolean> isEmptyListener;
    private boolean isErrorDisplayedOnSubmit;
    private Callback<Boolean> isErrorDisplayedOnSubmitCallback;
    private Callback<String> onChange;

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
                .doOnNext(__ -> et.setError(null))
                .skip(options.shouldValidateOnChange ? 1 : 0)
                .debounce(200, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s -> {
                    if (onChange != null) onChange.call(s);
                })
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
                .map(isNotEmpty -> {
                    if (isNotEmpty){
                        boolean isValid = validate(et.getText().toString());
                        if (!isValid){
                            error();
                            return false;
                        }
                    }
                    return isNotEmpty;
                })
                .map(isValid -> new ValidationBean(et, til, isValid, this));
    }

    private void clearError() {
        if (til != null) til.setError(null);
    }

    private boolean isEmpty(String s) {
        boolean isEmpty = s.trim().isEmpty();
        if (isEmpty){
            errorMessage = options.emptyMessage();
            error();
        }
        return isEmpty;
    }

    void emitInitialValue() {
        if (initialValue == null || initialValue.equals("")) return;
        et.setText(initialValue);
    }

    public Validator initialValue(String value){
        if (initialValue == null || initialValue.equals("")) return this;
        et.setText(initialValue);

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

    void notifyEmpty(){
        if (isEmptyListener != null)
            isEmptyListener.call(et.getText().toString().isEmpty());
    }

    private void error(){
        if (options.shouldValidateOnChange){
            if (til == null) et.setError(errorMessage);
            else til.setError(errorMessage);
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public EditText getEt() {
        return et;
    }

    public Validator errorMessage(String msg){
        errorMessage = msg;
        return this;
    }

    public Validator onChange(Callback<String> callback){
        this.onChange = callback;
        return this;
    }

}
