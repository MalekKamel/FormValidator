package com.sha.formvalidator.validator;


import android.widget.EditText;

/**
 * This validator takes care of validating the edittext. The input will be valid only if the number is a valid credit card.
 *
 */
public class CreditCardValidator extends Validator {

    public CreditCardValidator() {
        super(null);
    }

    public CreditCardValidator(String errorMessage) {
        super(errorMessage);
    }

    public boolean isValid(EditText et) {
        try {
            return validateCardNumber(et.getText().toString());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates the credit card number using the Luhn algorithm
     *
     * @param cardNumber the credit card number
     * @return
     */
    public static boolean validateCardNumber(String cardNumber) throws NumberFormatException {
        int sum = 0, digit, addend;
        boolean doubled = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            digit = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (doubled) {
                addend = digit * 2;
                if (addend > 9) {
                    addend -= 9;
                }
            } else {
                addend = digit;
            }
            sum += addend;
            doubled = !doubled;
        }
        return (sum % 10) == 0;
    }
}
