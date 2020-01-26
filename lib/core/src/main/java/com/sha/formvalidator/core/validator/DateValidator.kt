package com.sha.formvalidator.core.validator

import android.annotation.SuppressLint
import com.sha.formvalidator.core.DefaultErrors
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class DateValidator(format: String) : AbsValidator<String>() {
    override var errorGenerator: ErrorGeneratorInterface = ErrorGenerator.create(DefaultErrors.date)
    private val formats: Array<String> = if (format.isNotEmpty())
        format.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    else
        arrayOf("DefaultDate", "DefaultTime", "DefaultDateTime")

    @SuppressLint("SimpleDateFormat")
    override fun validate(): Boolean {
        if (value == null) return false

        for (_format in formats) {

            val format: DateFormat = when (_format) {
                "DefaultDate" -> SimpleDateFormat.getDateInstance()

                "DefaultTime" -> SimpleDateFormat.getTimeInstance()

                "DefaultDateTime" -> SimpleDateFormat.getDateTimeInstance()

                else -> SimpleDateFormat(_format)
            }

            val date: Date?

            try {
                date = format.parse(value!!)
            } catch (e: ParseException) {
                return false
            }

            if (date != null) return true
        }

        return false
    }

}
