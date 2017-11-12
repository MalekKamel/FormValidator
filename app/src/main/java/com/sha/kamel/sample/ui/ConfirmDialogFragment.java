package com.sha.kamel.sample.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sha.kamel.formvalidator.util.Callback;
import com.sha.kamel.sample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sha on 11/11/17.
 */

public class ConfirmDialogFragment extends DialogFragment{

    private Callback<Boolean> callback;
    private String message;

    @BindView(R.id.tv_message)
    TextView tv_message;

    public static ConfirmDialogFragment newInstance(String message, Callback<Boolean> callback) {
        ConfirmDialogFragment fragment = new ConfirmDialogFragment();
        fragment.setMessage(message);
        fragment.setCallback(callback);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_dialog_confirm,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        tv_message.setText(message);
    }

    @OnClick({R.id.btn_confirm,
            R.id.btn_close})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_confirm:
                if (callback != null)
                    callback.call(true);
                dismiss();
                break;

            case R.id.btn_close:
                if (callback != null)
                    callback.call(false);
                dismiss();
                break;
        }
    }

    public void setCallback(Callback<Boolean> callback) {
        this.callback = callback;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ConfirmDialogFragment show(AppCompatActivity activity){
        show(activity.getSupportFragmentManager(),
                getClass().getSimpleName()
        );
        return this;
    }
}
