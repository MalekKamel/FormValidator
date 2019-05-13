package com.sha.formvalidator.validator;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateValidator extends Validator {
    private String[] formats;

    public DateValidator(String errorMessage, String _format) {
        super(errorMessage);

        formats = !TextUtils.isEmpty(_format) ?
                _format.split(";") :
                new String[]{"DefaultDate", "DefaultTime", "DefaultDateTime"};
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public boolean isValid(EditText et) {
        if (TextUtils.isEmpty(et.getText()))
            return true;
        String value = et.getText().toString();

        for (String _format : formats) {
            DateFormat format;

            switch (_format){
                case "DefaultDate":
                    format = SimpleDateFormat.getDateInstance();

                    break;

                case "DefaultTime":
                    format = SimpleDateFormat.getTimeInstance();

                    break;

                case "DefaultDateTime":
                    format = SimpleDateFormat.getDateTimeInstance();

                    break;

                default:
                    format = new SimpleDateFormat(_format);
                    break;

            }

            Date date;

            try {
                date = format.parse(value);
            } catch (ParseException e) {
                return false;
            }

            if (date != null) return true;
        }

        return false;
    }

}
