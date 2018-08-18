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
    private boolean isErrorDisplayedOnValidation;
    private BooleanConsumer shouldShowPasswordVisibilityToggle;
    private Consumer<String> onChange;

    public Validator(TextView tv) {
        this.tv = tv;
    }

    public Validator(TextView tv, TextInputLayout til) {
        this.tv = tv;
        this.til = til;
    }

    /**
     * Function used internally to prepare validator
     * @param options options
     */
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
                    if (isErrorDisplayedOnValidation){
                        isErrorDisplayedOnValidation = false;
                        if (shouldShowPasswordVisibilityToggle != null)
                            shouldShowPasswordVisibilityToggle.accept(false);
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
        tv.setError(null);
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

    /**
     * Call this function to set initial value
     * This function is useful when setting {@link FormValidator#validateOnChange()}
     * to true.
     * in this case, the initial value will be recognized internally in the library
     * and will be validated when text changes.
     * @param value initial value
     * @return this
     */
    public Validator initialValue(String value){
        if (initialValue == null || initialValue.equals("")) return this;
        tv.setText(initialValue);

        this.initialValue = value;
        return this;
    }

    /**
     * Listener that will be called if the text is empty
     * @param callback
     * @return
     */
    public Validator isEmptyListener(BooleanConsumer callback){
        this.isEmptyListener = callback;
        return this;
    }


    /**
     * This method is useful in one case,
     * What is the case?
     * When you use password visibility toggle in {@link android.widget.EditText}
     * and set error message, the error message will overlap with the toggle.
     * The solution is to toggle between error and the password toggle.
     * ex:
     * {@code
     *    new RequiredValidator(et_password)
     *                                 .shouldShowPasswordVisibilityToggle(show ->
     *                                         til_password.setPasswordVisibilityToggleEnabled(!show))
     * }
     * @param callback called with a boolean
     * @return this
     */
    public Validator shouldShowPasswordVisibilityToggle(BooleanConsumer callback){
        shouldShowPasswordVisibilityToggle = callback;
        return this;
    }

    Validator setErrorDisplayedOnValidation(boolean isDisplayed){
        isErrorDisplayedOnValidation = isDisplayed;

        if (shouldShowPasswordVisibilityToggle != null){
            shouldShowPasswordVisibilityToggle.accept(isDisplayed);
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

    /**
     *  Method used internally to get {@link TextView}
     * @return {@link TextView}
     */
    public TextView getTv() {
        return tv;
    }

    /**
     *  Use this method to set error message
     * @param msg error message
     * @return this
     */
    public Validator errorMessage(String msg){
        errorMessage = msg;
        return this;
    }

    /**
     * User this method to listen to text changes
     *  ex:
     * {@code
     *  new FixedLengthValidator(et_age, 2)
     *                                 .onChange(text -> {
     *                                     if (!text.isEmpty() && Integer.valueOf(text) < 15)
     *                                         cb_under15.setVisibility(View.VISIBLE);
     *                                     else
     *                                         cb_under15.setVisibility(View.GONE);
     *                                 })
     * }
     * @param callback method will receive the text after change
     * @return this
     */
    public Validator onChange(Consumer<String> callback){
        this.onChange = callback;
        return this;
    }



}
