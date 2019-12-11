package com.sha.formvalidatorsample.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sha.formvalidator.util.ValidatorFactory;
import com.sha.formvalidator.validator.CreditCardValidator;
import com.sha.formvalidator.validator.pattern.EmailValidator;
import com.sha.formvalidator.widget.FormAutoCompleteTextView;
import com.sha.formvalidator.widget.FormEditText;
import com.sha.formvalidatorsample.R;

public class AutoCompleteTextViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);
    }

    public void onClickValidate(View v) {
        FormAutoCompleteTextView autoCompleteTv = findViewById(R.id.autoCompleteTv);
        if (autoCompleteTv.validate()) {
            Toast.makeText(this, "Valid", Toast.LENGTH_LONG).show();
        }
    }
}
