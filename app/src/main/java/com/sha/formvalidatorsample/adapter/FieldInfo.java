package com.sha.formvalidatorsample.adapter;

import com.sha.formvalidatorsample.R;
import com.sha.formvalidatorsample.ui.AutoCompleteTextViewActivity;
import com.sha.formvalidatorsample.ui.EmailOrCreditCardActivity;
import com.sha.formvalidatorsample.ui.PasswordValidatorActivity;
import com.sha.formvalidatorsample.ui.PrefixAndRangeValidatorActivity;

import java.util.Arrays;
import java.util.List;

class FieldInfo {

    static List<FieldItem> items = Arrays.asList(
            new FieldItem(
                    "Alpha With TextInputLayout",
                    R.layout.field_alpha_textinputlayout,
                    R.string.description_alpha),
            new FieldItem(
                    "Alpha",
                    R.layout.field_alpha,
                    R.string.description_alpha),
            new FieldItem(
                    "Person Name",
                    R.layout.field_personname,
                    R.string.description_person_name),
            new FieldItem(
                    "Person Full Name",
                    R.layout.field_personfullname,
                    R.string.description_person_full_name),
            new FieldItem(
                    "Date",
                    R.layout.field_date,
                    R.string.description_date),
            new FieldItem(
                    "Date Custom Format",
                    R.layout.field_date_custom,
                    R.string.description_date_custom),
            new FieldItem(
                    "Numeric only",
                    R.layout.field_numeric,
                    R.string.description_numeric),
            new FieldItem(
                    "Float Numeric Range",
                    R.layout.field_float_numeric_range,
                    R.string.float_numeric_range),
            new FieldItem(
                    "Email",
                    R.layout.field_email,
                    R.string.description_email),
            new FieldItem(
                    "Credit Card Number",
                    R.layout.field_creditcard,
                    R.string.description_credit_card),
            new FieldItem(
                    "Phone",
                    R.layout.field_phone,
                    R.string.description_phone),
            new FieldItem(
                    "Domain Name",
                    R.layout.field_domain_name,
                    R.string.description_domain_name),
            new FieldItem(
                    "IP Address",
                    R.layout.field_ip_address,
                    R.string.description_ip_address),
            new FieldItem(
                    "WEB Url",
                    R.layout.field_weburl,
                    R.string.description_web_url),
            new FieldItem(
                    "Regex",
                    R.layout.field_regex,
                    R.string.description_regex),
            new FieldItem(
                    "Custom Messages",
                    R.layout.field_phone_custommessages,
                    R.string.description_phone_custom_messages),
            new FieldItem(
                    "Allow Empty",
                    R.layout.field_allowempty,
                    R.string.description_allow_empty),
            new FieldItem(
                    "Custom validation type",
                    R.layout.field_custom_validation_type,
                    R.string.custom_validation_type_description),
            new FieldItem("Email OR CreditCard", EmailOrCreditCardActivity.class),
            new FieldItem("Suffix AND Range", PrefixAndRangeValidatorActivity.class),
            new FieldItem("Password matching",PasswordValidatorActivity .class),
            new FieldItem("AutoComplete TextView", AutoCompleteTextViewActivity.class)
    );

}
