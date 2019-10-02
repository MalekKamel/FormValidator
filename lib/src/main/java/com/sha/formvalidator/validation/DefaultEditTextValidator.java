package com.sha.formvalidator.validation;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewParent;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.sha.formvalidator.R;
import com.sha.formvalidator.model.Options;
import com.sha.formvalidator.model.ValidationType;
import com.sha.formvalidator.util.ImplicitValidatorFactory;
import com.sha.formvalidator.validator.Validator;
import com.sha.formvalidator.validator.composite.AndValidator;
import com.sha.formvalidator.validator.composite.CompositeValidator;

/**
 * Default implementation of an {@link EditTextValidator}
 */
public class DefaultEditTextValidator implements EditTextValidator {
    /**
     * The custom validators setted using
     */
    protected CompositeValidator mValidator;
    protected EditText editText;

    private Options options = new Options();

    /**
     * support dynamic new DefaultEditTextValidator() ,used for Java call
     *
     * @param editText EditText
     * @param context Context
     */
    public DefaultEditTextValidator(EditText editText, Context context) {
        setupDynamically(editText, context);
    }

    public DefaultEditTextValidator(EditText editText, AttributeSet attrs, Context context) {
        setupFromXml(editText, attrs, context);
    }

    private void setupDynamically(EditText editText, Context context) {
        options.validationType = ValidationType.NOT_EMPTY;
        this.editText = editText;
        setupValidator(context);
    }

    private void setupFromXml(
            EditText editText,
            AttributeSet attrs,
            Context context
    ) {
        setupAttrs(attrs, context);
        this.editText = editText;
        setupValidator(context);
    }

    private void setupAttrs(AttributeSet attrs, Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormEditText);

        options.required = typedArray.getBoolean(R.styleable.FormEditText_required, true);

        int validationTypeValue = typedArray.getInt(R.styleable.FormEditText_validationType, ValidationType.NOT_DETECTABLE.value);
        options.validationType = ValidationType.fromValue(validationTypeValue);

        options.errorMessage = typedArray.getString(R.styleable.FormEditText_errorMessage);
        options.customValidationType = typedArray.getString(R.styleable.FormEditText_customValidationType);
        options.regex = typedArray.getString(R.styleable.FormEditText_regex);
        options.emptyErrorMessage = typedArray.getString(R.styleable.FormEditText_requiredErrorMessage);
        options.dateFormat = typedArray.getString(R.styleable.FormEditText_dateFormat);

        switch (options.validationType){
            case NUMERIC_RANGE:
                options.minNumber = typedArray.getInt(R.styleable.FormEditText_minNumber, Integer.MIN_VALUE);
                options.maxNumber = typedArray.getInt(R.styleable.FormEditText_maxNumber, Integer.MAX_VALUE);
                break;

            case FLOAT_NUMERIC_RANGE:
                options.floatMinNumber = typedArray.getFloat(R.styleable.FormEditText_floatMinNumber, Float.MIN_VALUE);
                options.floatMaxNumber = typedArray.getFloat(R.styleable.FormEditText_floatMaxNumber, Float.MAX_VALUE);
                break;
        }

        typedArray.recycle();
    }

    @Override
    public void addValidator(Validator validator) {

        if (validator == null)
            throw new IllegalArgumentException("Validator argument should not be null");

        mValidator.enqueue(validator);
    }

    @Override
    public void setupValidator(Context context) {

        mValidator = new AndValidator();

        addValidator(ImplicitValidatorFactory.validator(options, context));
    }

    @Override
    public boolean validate() {
        return validate(true);
    }

    @Override
    public boolean validate(boolean showError) {
        boolean isValid = mValidator.isValid(editText);

        if (!isValid && showError) showError();

        return isValid;
    }

    @Override
    public void showError() {
        if (!mValidator.hasErrorMessage()) return;

        if (hasTextInputLayout()){
            textInputLayout().setError(mValidator.getErrorMessage());
            return;
        }

        editText.setError(mValidator.getErrorMessage());
    }

    private boolean hasTextInputLayout() {
        ViewParent parent = editText.getParent().getParent();
        return parent instanceof TextInputLayout;
    }

    private TextInputLayout textInputLayout() {
        return (TextInputLayout) editText.getParent().getParent();
    }

    public boolean isErrorShown() {
        try {
            TextInputLayout parent = (TextInputLayout) editText.getParent();
            return true; // might sound like a bug. but there's no way to know if the error is shown (not with public api)
        } catch (Throwable e) {
            return !TextUtils.isEmpty(editText.getError());
        }
    }

    // >>>>>>>>>> getters & setters

    public void setRegex(String regex, Context context) {
        options.validationType = ValidationType.REGEX;
        options.regex = regex;
        setupValidator(context);
    }

    public void setRequired(boolean required, Context context) {
        options.required = required;
        setupValidator(context);
    }

    public void setErrorMessage(String errorString) {
        options.errorMessage = errorString;
        setupValidator(editText.getContext());
    }

    public void setValidationType(ValidationType validationType) {
        options.validationType = validationType;
        setupValidator(editText.getContext());
    }

    public String getRegex() {
        return options.regex;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public String getErrorMessage() {
        return options.errorMessage;
    }

    public ValidationType getValidationType() {
        return options.validationType;
    }

    @Override
    public boolean isRequired() {
        return options.required;
    }
}
