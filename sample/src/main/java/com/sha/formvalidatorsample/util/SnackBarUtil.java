package com.sha.formvalidatorsample.util;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.sha.formvalidatorsample.R;

public class SnackBarUtil {

    public static void gotIt(final View view, String text) {
        new Handler().postDelayed(() -> multilineSnackbar(
                Snackbar.make(
                        view, text, Snackbar.LENGTH_INDEFINITE)
                        .setAction("Got it", view1 -> {

                        })
        ).show(), 200);
    }

    private static Snackbar multilineSnackbar(Snackbar snackbar) {
        TextView textView = snackbar.getView().findViewById(R.id.snackbar_text);
        textView.setMaxLines(5);
        return snackbar;
    }
}
