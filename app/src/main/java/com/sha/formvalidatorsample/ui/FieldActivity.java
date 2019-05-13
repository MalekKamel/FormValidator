package com.sha.formvalidatorsample.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sha.formvalidator.widget.FormEditText;
import com.sha.formvalidatorsample.R;

public class FieldActivity extends Activity {
    private static final String EXTRA_LAYOUT_RES = "EXTRA_LAYOUT_RES";
    private static final String EXTRA_LAYOUT_EXPL_STR_RES = "EXTRA_LAYOUT_EXPL_STR_RES";
    private static final String EXTRA_TITLE = "EXTRA_TITLE";

    public static Intent buildIntent(
            Context ctx,
            String title,
            int layoutRes,
            int explanationString
    ) {
        Intent toRet = new Intent(ctx, FieldActivity.class);
        toRet.putExtra(EXTRA_TITLE, title);
        toRet.putExtra(EXTRA_LAYOUT_RES, layoutRes);
        toRet.putExtra(EXTRA_LAYOUT_EXPL_STR_RES, explanationString);
        return toRet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);

        int fieldRes = getIntent().getIntExtra(EXTRA_LAYOUT_RES, 0);
        int descriptionRes = getIntent().getIntExtra(EXTRA_LAYOUT_EXPL_STR_RES, 0);
        String title = getIntent().getStringExtra(EXTRA_TITLE);

        FrameLayout flContainer = findViewById(R.id.fl);
        View view = LayoutInflater.from(this).inflate(fieldRes, flContainer, false);
        flContainer.addView(view);

        TextView tvDescription = findViewById(R.id.tv_description);
        tvDescription.setText(descriptionRes);

        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(title);
    }


    public void onClickValidate(View v) {
        FormEditText fdt = findViewById(R.id.et);
        if (!fdt.validate()) return;

        Toast.makeText(this, "Valid", Toast.LENGTH_LONG).show();
    }

}
