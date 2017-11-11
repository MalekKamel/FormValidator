package com.sha.kamel.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sha.kamel.formvalidator.FormValidator;
import com.sha.kamel.sample.validator.AgeValidator;
import com.sha.kamel.sample.validator.AreaValidator;
import com.sha.kamel.sample.validator.MobileValidator;
import com.sha.kamel.sample.validator.NameValidator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseExampleActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.et_age)
    EditText et_age;

    @BindView(R.id.et_area)
    EditText et_area;

    @BindView(R.id.et_mobile)
    EditText et_mobile;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    protected FormValidator<ClientInfo> formValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        formValidator = new FormValidator<>();
    }

    protected void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

