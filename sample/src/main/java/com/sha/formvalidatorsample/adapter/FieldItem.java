package com.sha.formvalidatorsample.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.sha.formvalidatorsample.ui.FieldActivity;

public class FieldItem {
    private Class<? extends Activity> clazz;
    public int layoutRes = -1;
    public int description;
    public String title;

    public FieldItem(String title, Class<? extends Activity> _clazz) {
        this.title = title;
        clazz = _clazz;
    }

    public FieldItem(String title, int layoutRes, int description) {
        this.title = title;
        this.layoutRes = layoutRes;
        this.description = description;
    }

    public void showDemo(Context ctx) {
        if (layoutRes != -1){
            ctx.startActivity(FieldActivity.buildIntent(ctx, title, layoutRes, description));
            return;
        }
        ctx.startActivity(new Intent(ctx, clazz));
    }

}
