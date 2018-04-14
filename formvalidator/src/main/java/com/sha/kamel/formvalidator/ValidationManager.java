package com.sha.kamel.formvalidator;

import android.view.View;
import android.widget.EditText;

import com.sha.kamel.formvalidator.util.Callback;
import com.sha.kamel.formvalidator.util.ConditionCallback;
import com.sha.kamel.formvalidator.util.Func;
import com.sha.kamel.formvalidator.util.IsValidCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Sha on 11/13/17.
 */

public abstract class ValidationManager<T> {

    protected List<Validator> validators = new ArrayList<>();
    protected Map<EditText, ValidationBean> beans = new HashMap<>();
    protected Map<EditText, String> texts = new HashMap<>();
    protected Observable<Object> source;
    protected PublishSubject<Object> validateOnEventPs;
    protected ValidationEvent validationEvent;
    protected FormValidatorMapperVa<T> mapperVa;
    protected FormValidatorMapper<T> mapper;
    protected Disposable sourceDisposable;
    protected List<Validator> passwordValidators = new ArrayList<>();
    private List<Disposable> etDisposableList = new ArrayList<>();

    protected ValidationOptions options = new ValidationOptions();

    public abstract FormValidator<T> with(View sourceView);
    public abstract ValidationEvent validationEvent();
    public abstract ValidationManager<T> with(View sourceView, Func invalidCallback);
    public abstract ValidationManager<T> add(Validator... validators);
    public abstract ValidationManager<T> add(Validator validator);
    public abstract ValidationManager<T> mapData(FormValidatorMapperVa<T> mapper);
    public abstract ValidationManager<T> map(FormValidatorMapper<T> mapper);
    public abstract Observable<T> asObservable();
    public abstract Observable<T> test();
    public abstract void subscribe(Callback<T> callback);
    public abstract boolean isAnyValid();
    public abstract boolean isAnyHasText();
    public abstract boolean isAllValid();
    public abstract String from(EditText et);
    public abstract void clearAll();
    protected boolean isAddedValidators;

    public ValidationManager<T> skipInitialValidation(boolean shouldSkip) {
        this.options.shouldSkipInitialValidation = shouldSkip;
        return this;
    }

    public ValidationManager<T> emptyMessage(String emptyMessage){
        options.emptyMessage = emptyMessage;
        return this;
    }

    public ValidationManager<T> passwordsNotIdenticalMessage(String message){
        options.passwordsNotIdenticalMessage = message;
        return this;
    }

    public ValidationManager<T> doIfInvalid(Func invalidCallback){
        options.invalidCallback = invalidCallback;
        return this;
    }

    public ValidationManager<T> also(
            IsValidCallback validate,
            Callback<Boolean> validationCallback
    ){
        options.also.add(validate);
        options.alsoInvalidCallbacks.add(validationCallback);
        return this;
    }

//    public ValidationManager<T> alsoIf(
//            ConditionCallback condition,
//            Validator validator,
//            Callback<String> errorMessage
//    ){
//        options.alsoIfValidAtorConditions.add(condition);
//        options.alsoIfValidators.add(validator);
//        options.alsoIfValidatorErrorMessages.add(errorMessage);
//        return this;
//    }

//    public ValidationManager<T> also(
//            EditText et,
//            Callback<String> errorMessage
//    ){
//        options.alsoEts.add(et);
//        options.alsoEtErrorMessages.add(errorMessage);
//        return this;
//    }
//
//    public ValidationManager<T> alsoIf(
//            ConditionCallback condition,
//            EditText et,
//            Callback<String> errorMessage
//    ){
//        options.alsoIfEtConditions.add(condition);
//        options.alsoIfEts.add(et);
//        options.alsoIfEtErrorMessages.add(errorMessage);
//        return this;
//    }
//
    public ValidationManager<T> alsoIf(
            ConditionCallback conditionCallback,
            IsValidCallback validate,
            Callback<Boolean> validationCallback
    ){
        options.alsoIfConditions.add(conditionCallback);
        options.alsoIf.add(validate);
        options.alsoIfInvalidCallbacks.add(validationCallback);
        return this;
    }

    public ValidationManager<T> validateOnChange(){
        options.shouldValidateOnChange = true;
        return this;
    }

    protected void throwExceptionsIfFound() {
        if (validators.isEmpty())
            throw new RuntimeException("You must add validators, call 'add(Validator)' method.");

        if (mapperVa == null && mapper == null)
            throw new RuntimeException("Must call 'map' or 'mapData' before 'asObservable");
    }

    protected T getMapperData() {
        String[] texts = new String[validators.size()];
        for (int i = 0 ; i < texts.length ; i++){
            Validator validator = validators.get(i);
            texts[i] = validator.et.getText().toString();
        }
        T data = null;
        if (mapper != null)
            data = mapper.call(this);
        else if (mapperVa != null)
            data = mapperVa.call(texts);

        if (data == null)
            throw  new RuntimeException("'map' can't return null.");
        return data;
    }

    protected void start(){
        throwExceptionsIfFound();

        for (Validator validator : validators){
            Disposable disposable = validator.start(options).subscribe(bean -> {
                beans.put(bean.getEt(), bean);
                texts.put(bean.getEt(), bean.text());
            });
            etDisposableList.add(disposable);
            validator.emitInitialValue();
        }
    }

    public void dispose(){
        if (sourceDisposable != null)
            sourceDisposable.dispose();
        for (Disposable d : etDisposableList){
            if (d != null)
                d.dispose();
        }
    }

    public ValidationManager<T> setOptions(ValidationOptions options) {
        this.options = options;
        return this;
    }
}
