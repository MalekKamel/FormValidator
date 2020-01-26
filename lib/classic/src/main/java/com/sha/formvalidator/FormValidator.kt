package com.sha.formvalidator

import android.view.View
import com.sha.formvalidator.helper.AnimationHelper
import com.sha.formvalidator.model.FormOptions

/**
 * The class responsible for validating fields
 */
class FormValidator<T : Validatable>(
        private var fields: List<T>,
        private var options: FormOptions = FormOptions.defaultOptions()
) {
    val isValid: Boolean
        get() {
            if(fields.isEmpty()) return false
            var isValid = true
            fields.forEach {
                val fieldValid = it.validate()
                isValid = fieldValid && isValid
                if(options.shakeOnError && !fieldValid) AnimationHelper.error(it as? View)
            }
            return isValid
        }

}
