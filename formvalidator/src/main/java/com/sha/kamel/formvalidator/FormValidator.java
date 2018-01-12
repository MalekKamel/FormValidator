package com.sha.kamel.formvalidator;

import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.sha.kamel.formvalidator.util.Callback;
import com.sha.kamel.formvalidator.util.Func;
import com.sha.kamel.formvalidator.validator.PasswordIdentical;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Sha on 10/24/17.
 */

public final class FormValidator<T> extends ValidationManager<T>{

    @Override
    public ValidationEvent validationEvent(){
        validateOnEventPs = PublishSubject.create();
        this.validationEvent = () -> validateOnEventPs.onNext("");
        return validationEvent;
    }

    @Override
    public FormValidator<T> with(View sourceView){
        with(sourceView, null);
        return this;
    }

    @Override
    public FormValidator<T> with(View sourceView, Func invalidCallback){
        if (sourceView == null)
            throw  new RuntimeException("View can't be null.");

        this.source = RxView.clicks(sourceView);
        options.invalidCallback = invalidCallback;
        return this;
    }

    @Override
    public FormValidator<T> add(Validator... validators){
        for (Validator validator : validators){
            add(validator);
        }
        return this;
    }

    @Override
    public FormValidator<T> add(Validator validator){
        if (validator instanceof PasswordIdentical)
            passwordValidators.add(validator);

        validators.add(validator);
        beans.put(validator.et, new ValidationBean(validator.et, validator.til, false, validator));
        texts.put(validator.et, "");
        return this;
    }

    @Override
    public FormValidator<T> mapData(FormValidatorMapperVa<T> mapper){
        this.mapperVa = mapper;
        return this;
    }

    @Override
    public FormValidator<T> map(FormValidatorMapper<T> mapper){
        this.mapper = mapper;
        return this;
    }

    @Override
    public Observable<T> asObservable(){


        PublishSubject<T> ps = PublishSubject.create();

        sourceDisposable = getSourceObservable().subscribe(o -> {
                    if (isAllValid()){
                        boolean isPasswordValid = isPasswordsValid();
                        if (isPasswordValid){
                            T data = getMapperData();
                            ps.onNext(data);
                        }
                    }
                }
        );
        start();
        return ps;
    }

    private Observable<Object> getSourceObservable(){
        if (source != null && validateOnEventPs != null)
            throw new RuntimeException("You must use either 'with(View)' or 'validationEvent(ValidationEvent)'.");

        if (source == null && validateOnEventPs == null)
            throw new RuntimeException("You must use 'with(View)' or 'validationEvent(ValidationEvent)'.");
        return source != null ? source : validateOnEventPs;
    }

    private boolean isPasswordsValid() {
        if (passwordValidators.size() < 1)
            return true; // no passwords to compare

        boolean isIdentical = isPasswordsIdentical();

        for (Validator validator : passwordValidators){
            validator.getEt().setError(isIdentical ? null : options.passwordsNotIdenticalMessage);
        }

        return isIdentical;
    }

    private boolean isPasswordsIdentical() {
        List<String> passwords = getPasswords();

        String password = passwords.get(0);
        for (String pass : passwords){
            if (!password.equals(pass))
                return false;
        }
        return true;
    }

    private List<String> getPasswords() {
        List<String> passwords = new ArrayList<>();
        for (Validator validator : passwordValidators){
            passwords.add(validator.getEt().getText().toString());
        }
        return passwords;
    }

    @Override
    public void subscribe(Callback<T> callback){
        getSourceObservable().subscribe(o -> {
            if (isAllValid()){
                T data = getMapperData();
                callback.call(data);
            }
        });
        start();
    }

    @Override
    public boolean isAnyValid(){
        for (ValidationBean bean : new ArrayList<>(beans.values())){
            if (bean.isValid()) return true;
        }
        return false;
    }

    @Override
    public boolean isAnyHasText(){
        for (ValidationBean bean : new ArrayList<>(beans.values())){
            if (!bean.getEt().getText().toString().isEmpty()) return true;
        }
        return false;
    }

    @Override
    public boolean isAllValid(){
        boolean isValid = true;
        for (ValidationBean bean : new ArrayList<>(beans.values())){
            if (!bean.isValid()){

                bean.validator().notifyEmpty();
                bean.validator().setErrorDisplayedOnSubmit(true);

                // if didn't validate on change, we must validate here to
                // show the error.
                if (!options.shouldValidateOnChange){
                    bean.setError(bean.validator().getErrorMessage());
                }

                isValid = false;
            }
        }
        if (!isValid  && options.invalidCallback != null) options.invalidCallback.call();
        return isValid;
    }

    @Override
    public String from(EditText et){
        return texts.get(et);
    }

    @Override
    public void clearAll(){
        for (ValidationBean bean : new ArrayList<>(beans.values())){
            bean.getEt().setText("");
        }
    }
}
