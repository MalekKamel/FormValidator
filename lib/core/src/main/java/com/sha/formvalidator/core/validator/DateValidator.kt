package com.sha.formvalidator.core.validator

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class DateValidator(errorMessage: String, _format: String) : TextValidator(errorMessage) {
    private val formats: Array<String> = if (_format.isNotEmpty())
        _format.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    else
        arrayOf("DefaultDate", "DefaultTime", "DefaultDateTime")

    @SuppressLint("SimpleDateFormat")
    override fun isValid(text: String): Boolean {
        if (text.isEmpty()) return false

        for (_format in formats) {

            val format: DateFormat = when (_format) {
                "DefaultDate" -> SimpleDateFormat.getDateInstance()

                "DefaultTime" -> SimpleDateFormat.getTimeInstance()

                "DefaultDateTime" -> SimpleDateFormat.getDateTimeInstance()

                else -> SimpleDateFormat(_format)
            }

            val date: Date?

            try {
                date = format.parse(text)
            } catch (e: ParseException) {
                return false
            }

            if (date != null) return true
        }

        return false
    }

}
