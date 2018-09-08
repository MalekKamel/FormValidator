package com.sha.kamel.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.sha.kamel.formvalidator.validator.FixedLengthValidator;
import com.sha.kamel.formvalidator.validator.RangeValidator;
import com.sha.kamel.sample.validator.MobileValidator;

import butterknife.BindView;
import butterknife.OnClick;

public class Example6Activity extends BaseExampleActivity {

    @BindView(R.id.checkBox)
    CheckBox checkBox;

    @BindView(R.id.cb_under15)
    CheckBox cb_under15;

    protected int resourceLayout(){
        return R.layout.activity_content_6;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        formValidator
                .add(
                        new RangeValidator(et_name, 4, 100),
                        new FixedLengthValidator(et_age, 2)
                                .onChange(text -> {
                                    boolean isUnder15 = !text.isEmpty() && Integer.valueOf(text) < 15;
                                    cb_under15.setVisibility(isUnder15 ? View.VISIBLE : View.GONE);
                                }),
                        new MobileValidator(et_mobile),
                        new RangeValidator(et_area, 3, 25))
                .map(validator -> new ClientInfo()
                        .setName(validator.textOf(et_name))
                        .setAge(validator.textOf(et_name))
                        .setMobile(validator.textOf(et_name))
                        .setArea(validator.textOf(et_name)))
                .validate(
                        () -> checkBox.isChecked(),
                        isValid ->{
                            if (!isValid) toast("You must accept terms and conditions!");
                        }
                )
                .validateIf(this::isUnder15,
                        () -> cb_under15.isChecked(),
                        isValid -> {
                            if (!isValid) toast("You must confirm content is adequate for you.");
                        })
                .doIfInvalid(() -> toast("Fill required data."))
                .asObservable()
                .doOnNext(data -> toast("Saving Client info"))
//               .flatMap(data -> {}) --> save in database
//               .flatMap(data -> {}) --> send to server
                .subscribe(
                        data -> toast("Saved data successfully."),
                        Throwable::printStackTrace);
    }

    private boolean isUnder15() {
        return !et_age.getText().toString().isEmpty() && Integer.valueOf(et_age.getText().toString()) < 15;
    }

    @OnClick(R.id.btn_submit)
    public void onClick(View v) {
        super.onClick(v);
        formValidator.startValidation();
    }

}

