package com.sha.kamel.formvalidator;


import android.support.design.widget.TextInputLayout;
import android.widget.TextView;

import com.annimon.stream.function.BooleanConsumer;
import com.annimon.stream.function.Consumer;
import com.sha.kamel.formvalidator.util.RxChangeListener;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Sha on 11/8/17.
 */

public abstract class Validator {
    TextInputLayout til;

    protected TextView tv;
    protected String errorMessage;

    protected abstract boolean validate(String s);

    private String initialValue;
    protected ValidationOptions options;

    private BooleanConsumer isEmptyListener;
    private boolean isErrorDisplayedOnSubmit;
    private BooleanConsumer isErrorDisplayedOnSubmitCallback;
    private Consumer<String> onChange;

    public Validator(TextView tv) {
        this.tv = tv;
    }

    public Validator(TextView tv, TextInputLayout til) {
        this.tv = tv;
        this.til = til;
    }

    public final Observable<ValidationBean> prepare(ValidationOptions options){
        this.options = options;
        return new RxChangeListener(tv)
                .asObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(__ -> tv.setError(null))
                .skip(options.shouldValidateOnChange ? 1 : 0)
                .debounce(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s -> {
                    if (onChange != null) onChange.accept(s);
                })
                .doOnNext(s -> {
                    if (isErrorDisplayedOnSubmit){
                        isErrorDisplayedOnSubmit = false;
                        if (isErrorDisplayedOnSubmitCallback != null)
                            isErrorDisplayedOnSubmitCallback.accept(false);
                    }
                })
                .doOnNext(s -> {
                    if (isEmptyListener != null){
                        isEmptyListener.accept(s.isEmpty());
                    }
                })
                .doOnNext(s -> clearError())
                .map(s -> !isEmpty(s)) // If empty, stop. We don't want to add others until this succeed
                .map(isNotEmpty -> {
                    if (isNotEmpty){
                        boolean isValid = validate(tv.getText().toString());
                        if (!isValid){
                            error();
                            return false;
                        }
                    }
                    return isNotEmpty;
                })
                .map(isValid -> new ValidationBean(tv, til, isValid, this));
    }

    private void clearError() {
        if (til != null) til.setError(null);
    }

    private boolean isEmpty(String s) {
        boolean isEmpty = s.trim().isEmpty();
        if (isEmpty){
            errorMessage = options.getMessageIfEmpty();
            error();
        }
        return isEmpty;
    }

    void emitInitialValue() {
        if (initialValue == null || initialValue.equals("")) return;
        tv.setText(initialValue);
    }

    public Validator initialValue(String value){
        if (initialValue == null || initialValue.equals("")) return this;
        tv.setText(initialValue);

        this.initialValue = value;
        return this;
    }

    public Validator isEmptyListener(BooleanConsumer callback){
        this.isEmptyListener = callback;
        return this;
    }

    public boolean isErrorDisplayedOnSubmit(){
        return isErrorDisplayedOnSubmit;
    }

    public Validator isErrorDisplayedOnSubmit(BooleanConsumer callback){
        isErrorDisplayedOnSubmitCallback = callback;
        return this;
    }

    public Validator setErrorDisplayedOnSubmit(boolean isDisplayed){
        isErrorDisplayedOnSubmit = isDisplayed;

        if (isErrorDisplayedOnSubmitCallback != null){
            isErrorDisplayedOnSubmitCallback.accept(isDisplayed);
        }

        return this;
    }

    void notifyEmpty(){
        if (isEmptyListener != null)
            isEmptyListener.accept(tv.getText().toString().isEmpty());
    }

    private void error(){
        if (options.shouldValidateOnChange){
            if (til == null) tv.setError(errorMessage);
            else til.setError(errorMessage);
        }
    }

    public String getErrorMessage() {
        if (isEmpty(tv.getText().toString()))
            return options.getMessageIfEmpty();
        return errorMessage;
    }

    public TextView getTv() {
        return tv;
    }

    public Validator errorMessage(String msg){
        errorMessage = msg;
        return this;
    }

    public Validator onChange(Consumer<String> callback){
        this.onChange = callback;
        return this;
    }



}
