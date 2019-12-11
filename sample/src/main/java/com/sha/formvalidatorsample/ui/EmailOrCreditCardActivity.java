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
import com.sha.formvalidator.widget.FormEditText;
import com.sha.formvalidatorsample.R;

public class EmailOrCreditCardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);

        FrameLayout flContainer = findViewById(R.id.fl);
        TextView tvDescription = findViewById(R.id.tv_description);
        TextView tvTitle = findViewById(R.id.tv_title);

        flContainer.addView(LayoutInflater.from(this).inflate(R.layout.field_email_or_creditcard, flContainer, false));
        tvDescription.setText(R.string.description_email_or_credit);
        tvTitle.setText(R.string.email_or_credit_title);

        //Interesting stuff starts here

        FormEditText fdt = findViewById(R.id.et);

        fdt.addValidator(
                ValidatorFactory.anyValid(
                        "This is neither a creditcard or an email",
                        new CreditCardValidator(null), // we specify null as the message string cause the Or validator will use his own message
                        new EmailValidator(null) // same here for null
                )
        );
    }

    public void onClickValidate(View v) {
        FormEditText fdt = findViewById(R.id.et);
        if (fdt.validate()) {
            Toast.makeText(this, "Valid", Toast.LENGTH_LONG).show();
        }
    }
}
