package com.sha.formvalidator.validator

import android.annotation.SuppressLint
import android.text.TextUtils
import android.widget.EditText

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date


class DateValidator(errorMessage: String, _format: String) : Validator(errorMessage) {
    private val formats: Array<String>

    init {

        formats = if (!TextUtils.isEmpty(_format))
            _format.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        else
            arrayOf("DefaultDate", "DefaultTime", "DefaultDateTime")
    }

    @SuppressLint("SimpleDateFormat")
    override fun isValid(et: EditText): Boolean {
        if (TextUtils.isEmpty(et.text))
            return true
        val value = et.text.toString()

        for (_format in formats) {
            val format: DateFormat

            when (_format) {
                "DefaultDate" -> format = SimpleDateFormat.getDateInstance()

                "DefaultTime" -> format = SimpleDateFormat.getTimeInstance()

                "DefaultDateTime" -> format = SimpleDateFormat.getDateTimeInstance()

                else -> format = SimpleDateFormat(_format)
            }

            val date: Date?

            try {
                date = format.parse(value)
            } catch (e: ParseException) {
                return false
            }

            if (date != null) return true
        }

        return false
    }

}
