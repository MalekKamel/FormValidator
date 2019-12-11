package com.sha.formvalidatorsample.adapter

import com.sha.formvalidatorsample.R
import com.sha.formvalidatorsample.ui.AutoCompleteTextViewActivity
import com.sha.formvalidatorsample.ui.EmailOrCreditCardActivity
import com.sha.formvalidatorsample.ui.PasswordValidatorActivity
import com.sha.formvalidatorsample.ui.PrefixAndRangeValidatorActivity

import java.util.Arrays

internal object FieldInfo {

    var items = Arrays.asList(
            FieldItem(
                    "Alpha With TextInputLayout",
                    R.layout.field_alpha_textinputlayout,
                    R.string.description_alpha),
            FieldItem(
                    "Alpha",
                    R.layout.field_alpha,
                    R.string.description_alpha),
            FieldItem(
                    "Person Name",
                    R.layout.field_personname,
                    R.string.description_person_name),
            FieldItem(
                    "Person Full Name",
                    R.layout.field_personfullname,
                    R.string.description_person_full_name),
            FieldItem(
                    "Date",
                    R.layout.field_date,
                    R.string.description_date),
            FieldItem(
                    "Date Custom Format",
                    R.layout.field_date_custom,
                    R.string.description_date_custom),
            FieldItem(
                    "Numeric only",
                    R.layout.field_numeric,
                    R.string.description_numeric),
            FieldItem(
                    "Float Numeric Range",
                    R.layout.field_float_numeric_range,
                    R.string.float_numeric_range),
            FieldItem(
                    "Email",
                    R.layout.field_email,
                    R.string.description_email),
            FieldItem(
                    "Credit Card Number",
                    R.layout.field_creditcard,
                    R.string.description_credit_card),
            FieldItem(
                    "Phone",
                    R.layout.field_phone,
                    R.string.description_phone),
            FieldItem(
                    "Domain Name",
                    R.layout.field_domain_name,
                    R.string.description_domain_name),
            FieldItem(
                    "IP Address",
                    R.layout.field_ip_address,
                    R.string.description_ip_address),
            FieldItem(
                    "WEB Url",
                    R.layout.field_weburl,
                    R.string.description_web_url),
            FieldItem(
                    "Regex",
                    R.layout.field_regex,
                    R.string.description_regex),
            FieldItem(
                    "Custom Messages",
                    R.layout.field_phone_custommessages,
                    R.string.description_phone_custom_messages),
            FieldItem(
                    "Allow Empty",
                    R.layout.field_allowempty,
                    R.string.description_allow_empty),
            FieldItem(
                    "Custom validation type",
                    R.layout.field_custom_validation_type,
                    R.string.custom_validation_type_description),
            FieldItem("Email OR CreditCard", EmailOrCreditCardActivity::class.java),
            FieldItem("Suffix AND Range", PrefixAndRangeValidatorActivity::class.java),
            FieldItem("Password matching", PasswordValidatorActivity::class.java),
            FieldItem("AutoComplete TextView", AutoCompleteTextViewActivity::class.java)
    )

}
