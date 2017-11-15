package com.sha.kamel.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sha.kamel.formvalidator.FormValidator;
import com.sha.kamel.formvalidator.ValidationManager;
import com.sha.kamel.formvalidator.ValidatorBuilder;
import com.sha.kamel.sample.ui.ConfirmDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    protected ValidationManager<ClientInfo> formValidator;

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

    @OnClick({R.id.btn_clear, R.id.btn_back})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_clear:
                formValidator.clearAll();
                break;

           case R.id.btn_back:
               // You can also use 'ormValidator.isAnyHasText()'
               if (formValidator.isAnyValid()){
                   ConfirmDialogFragment.newInstance(
                           getString(R.string.data_will_be_removed),
                           isConfirmed ->{
                               if (isConfirmed)
                                   onBackPressed();
                           }).show(this);
               }
               else onBackPressed();

               break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        formValidator.dispose();
    }
}

