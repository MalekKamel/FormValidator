package com.sha.kamel.formvalidator;

import android.view.View;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.sha.kamel.formvalidator.util.Condition;
import com.sha.kamel.formvalidator.validator.PasswordIdentical;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Sha on 10/24/17.
 */

public class FormValidator<T> extends ValidationManager<T>{

    private boolean isAllValid = true;

    /**
     * Call this method to start validation
     * @return this
     */
    @Override
    public ValidationManager startValidation() {
        if (isAllValid()){
            boolean isPasswordValid = isPasswordValid();
            if (isPasswordValid){
                T data = getMapperData();

                if (toObservable != null && data != null) toObservable.onNext(data);

                if (subscribeWithData != null && data != null) subscribeWithData.accept(data);

                if (subscribe != null) subscribe.run();
            }
        }
        return this;
    }


    /**
     * Add views to start validation when
     * clicking
     * */
    @Override
    public FormValidator<T> with(View... views){
        if (views == null || views.length == 0)
            throw  new IllegalStateException("View can't be null.");

        Stream.of(views)
                .forEach(sourceView -> sourceView.setOnClickListener(v -> startValidation()));

        return this;
    }

    /**
     * Add one or more {@link Validator} to validate a TextView
     *
     * @param validators instances of {@link Validator}
     * @return this
     * */
    @Override
    public FormValidator<T> add(Validator... validators){
        Stream.of(validators).forEach(this::add);
        return this;
    }

    /**
     * Add a {@link Validator} to validate a TextView
     *
     * @param validator instance of {@link Validator}
     * @return this
     * */
    @Override
    public FormValidator<T> add(Validator validator){
        isAddedValidators = true;

        if (validator instanceof PasswordIdentical)
            passwordValidators.add(validator);

        validators.add(validator);
        beans.put(validator.tv, new ValidationBean(validator.tv, validator.til, false, validator));
        texts.put(validator.tv, "");
        return this;
    }

    /**
     * Call this method to map data by the index of
     * each validator you added sequentially.
     *
     * ex:
     * {@code
     *      formValidator.add(
     *                               new RangeValidator(et_name, 4, 100).initialValue("Shaban Kamel"),
     *                               new FixedLengthValidator(et_age, 2),
     *                               new MobileValidator(et_mobile),
     *                               new RangeValidator(et_area, 3, 25))
     *       .mapIndexed(texts -> new ClientInfo()
     *                               .setName(texts[0])
     *                               .setAge(texts[1])
     *                               .setMobile(texts[2])
     *                               .setArea(texts[3]))
     *
     * }
     *
     * Notice that the data will be retrieved according to the order
     * of validators you added in add method.
     * */
    /**
     *
     * @param mapper a function receiving list of strings according to the order
     *               of validators
     * @return this
     */
    @Override
    public FormValidator<T> mapIndexed(FormValidatorMapperVa<T> mapper){
        this.mapperVa = mapper;
        return this;
    }

    /**
     * Call this method if you want to map the dat yourself
     * The library will pass you an instance of {@link FormValidator}
     * That you can use to get text of each validator easily
     *
     * ex:
     * {@code
     *     formValidator.map(validator -> new ClientInfo()
     *                         .setName(validator.textOf(et_name))
     *                         .setAge(validator.textOf(et_age))
     *                         .setMobile(validator.textOf(et_mobile))
     *                         .setArea(validator.textOf(et_area)))
     * }
     * @param mapper a function receiving an instance of {@link FormValidator}
     * @return this
     */
    @Override
    public FormValidator<T> map(FormValidatorMapper<T> mapper){
        this.mapper = mapper;
        return this;
    }

    /**
     * Call this method to return {@link Observable}
     * to receive data after successful validation
     * @return this
     */
    @Override
    public Observable<T> asObservable(){
        toObservable = PublishSubject.create();
        prepareValidators();
        return toObservable;
    }

    /**
     * Call this method if you want to test {@link FormValidator}
     * data
     * @return {@link Observable} with data
     */
    @Override
    public Observable<T> test(){
        for (Validator validator : validators){
            texts.put(validator.tv, validator.tv.getText().toString());
        }
        return Observable.just(getMapperData());
    }

    private boolean isPasswordValid() {
        if (passwordValidators.isEmpty() || passwordValidators.size() < 2)
            return true; // no passwords to compare

        boolean isIdentical = isPasswordsIdentical();

        for (Validator validator : passwordValidators){
            validator.getTv().setError(isIdentical ? null :validator.getErrorMessage());
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
            passwords.add(validator.getTv().getText().toString());
        }
        return passwords;
    }

    /**
     * Call this method to check if any field is valid.
     * @return true if any valid.
     */
    @Override
    public boolean isAnyValid(){
       return Stream.of(beans.values())
                .anyMatch(ValidationBean::isValid);
    }

    /**
     * Call this method to check if any field has text.
     * @return true if any has text.
     */
    @Override
    public boolean isAnyHasText(){
       return Stream.of(beans.values())
                .anyMatch(bean -> !bean.getTv().getText().toString().isEmpty());
    }


    @Override
    protected boolean isAllValid(){
        isAllValid = true;

        // validators
        validateValidators();

        // validate
        validateAlsoCallback();

        // validate if
        validateAlsoIfCallback();

        if (!isAllValid  && options.invalidCallback != null) options.invalidCallback.run();
        return isAllValid;
    }

    private void validateValidators() {
        Stream.of(beans.values())
                .filter(bean -> {
                    if (bean.validator() instanceof Conditional){
                        Condition condition = ((Conditional)bean.validator()).condition();
                        if (condition == null) throw new IllegalStateException("Conditional validator must not have NULL condition.");
                        return condition.call(bean.text());
                    }
                    return true;
                })
                .forEach(bean -> {
                    boolean isValid = bean.isValid();
                    if (!isValid){

                        bean.validator().notifyEmpty();
                        bean.validator().setErrorDisplayedOnValidation(true);

                        // if didn't validate on change, we must validate here to
                        // show the error.
                        if (!options.shouldValidateOnChange)
                            bean.setError(bean.validator().getErrorMessage());

                        isAllValid = false;
                    }
                });
    }

    private void validateAlsoCallback() {
        if (!options.validate.isEmpty()){
            Stream.of(options.validate)
                    .forEachIndexed((i, callback) -> {
                        boolean valid = callback.getAsBoolean();
                        options.validateConditionValidation.get(i).accept(valid);
                        if (!valid) isAllValid = false;
                    });
        }
    }

    private void validateAlsoIfCallback() {
        if (!options.validateIfConditions.isEmpty()){
            Stream.of(options.validateIfConditions)
                    .forEachIndexed((i, callback) -> {
                        boolean valid = callback.getAsBoolean();
                        options.validateIfConditionValidation.get(i).accept(valid);
                        if (!valid) isAllValid = false;
                    });
        }
    }

    /**
     * Call this method to get text of specific {@link TextView}
     * @param tv {@link TextView}
     * @return text of {@link TextView}
     */
    @Override
    public String textOf(TextView tv){
        return texts.get(tv);
    }

    /**
     * Call this method to clear all fields
     * @return this
     */
    @Override
    public FormValidator<T> clearAll(){
        Stream.of(beans.values())
                .forEach(bean -> bean.getTv().setText(""));

        return this;
    }
}
