package com.sha.formvalidator.textview.validator


/**
 * This validator takes care of validating the edittext. The input will be valid only if the number is a valid credit card.
 *
 */
class CreditCardValidator(errorMessage: String = "") : TextValidator(errorMessage) {

    override fun isValid(text: String): Boolean {
        return try {
            validateCardNumber(text)
        } catch (e: Exception) {
            false
        }
    }

    companion object {
        /**
         * Validates the credit card number using the Luhn algorithm
         *
         * @param cardNumber the credit card number
         * @return
         */
        @Throws(NumberFormatException::class)
        fun validateCardNumber(cardNumber: String): Boolean {
            var sum = 0
            var digit: Int
            var addend: Int
            var doubled = false
            for (i in cardNumber.length - 1 downTo 0) {
                digit = Integer.parseInt(cardNumber.substring(i, i + 1))
                if (doubled) {
                    addend = digit * 2
                    if (addend > 9) {
                        addend -= 9
                    }
                } else {
                    addend = digit
                }
                sum += addend
                doubled = !doubled
            }
            return sum % 10 == 0
        }
    }
}
