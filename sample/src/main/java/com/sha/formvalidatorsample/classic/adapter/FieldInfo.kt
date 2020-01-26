package com.sha.formvalidatorsample.classic.adapter

import com.sha.formvalidatorsample.R
import com.sha.formvalidatorsample.classic.ui.AutoCompleteTextViewActivity
import com.sha.formvalidatorsample.classic.ui.EmailOrCreditCardActivity
import com.sha.formvalidatorsample.classic.ui.PasswordValidatorActivity
import com.sha.formvalidatorsample.classic.ui.PrefixAndRangeValidatorActivity

internal object FieldInfo {

    var items = listOf(
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
                    "Float Range",
                    R.layout.field_float_numeric_range,
                    R.string.float_numeric_range),
            FieldItem(
                    "Email",
                    R.layout.field_email,
                    R.string.description_email),
            FieldItem(
                    "Credit Card",
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
                    "Optional",
                    R.layout.field_optional,
                    R.string.description_allow_empty),
            FieldItem("Email Or Credit", EmailOrCreditCardActivity::class.java),
            FieldItem("Suffix And Range", PrefixAndRangeValidatorActivity::class.java),
            FieldItem("Password matching", PasswordValidatorActivity::class.java),
            FieldItem("AutoComplete", AutoCompleteTextViewActivity::class.java)
    )

}
