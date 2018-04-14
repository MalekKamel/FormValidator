package com.sha.kamel.sample;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.sha.kamel.formvalidator.ValidationEvent;
import com.sha.kamel.formvalidator.validator.ConditionalEmailValidator;
import com.sha.kamel.formvalidator.validator.FixedLengthValidator;
import com.sha.kamel.formvalidator.validator.RangeValidator;
import com.sha.kamel.sample.validator.MobileValidator;

import butterknife.BindView;
import butterknife.OnClick;

public class Example7Activity extends BaseExampleActivity {

    private ValidationEvent validationEvent;

    @BindView(R.id.checkBox)
    CheckBox checkBox;

    @BindView(R.id.et_email)
    EditText et_email;

    @BindView(R.id.til_email)
    TextInputLayout til_email;

    protected int resourceLayout(){
        return R.layout.activity_content_7;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validationEvent = formValidator.validationEvent();

        formValidator
                .add(
                        new ConditionalEmailValidator(et_email, text -> checkBox.isChecked()),
                        new RangeValidator(et_name, 4, 100),
                        new FixedLengthValidator(et_age, 2),
                        new MobileValidator(et_mobile),
                        new RangeValidator(et_area, 3, 25)
                )
                .map(validator -> new ClientInfo()
                        .setName(validator.from(et_name))
                        .setAge(validator.from(et_name))
                        .setMobile(validator.from(et_name))
                        .setArea(validator.from(et_name)))
                .doIfInvalid(() -> toast("Fill required data."))
                .asObservable()
                .doOnNext(data -> toast("Saving Client info"))
//               .flatMap(data -> {}) --> save in database
//               .flatMap(data -> {}) --> send to server
                .subscribe(
                        data -> toast("Saved data successfully."),
                        Throwable::printStackTrace);
    }

    @OnClick({
            R.id.btn_submit,
            R.id.checkBox
    })
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_submit:
                validationEvent.validate();
                break;

            case R.id.checkBox:
                til_email.setVisibility(checkBox.isChecked() ? View.VISIBLE : View.GONE);
                break;
        }
    }

}

