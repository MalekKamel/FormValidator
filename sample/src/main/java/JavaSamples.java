import com.sha.formvalidator.Validators;
import com.sha.formvalidator.core.validator.Validator;
import com.sha.formvalidator.widget.FormEditText;

import java.util.Arrays;

/**
 * This class includes samples for Java developers
 * Note that this class is not supposed to run cause it just for
 */
public class JavaSamples {

    private FormEditText et;
    private FormEditText other;

    @SuppressWarnings("unchecked")
    public void samples() {

        // create email or credit validator
        Validator<String> emailOrCreditValidator = Validators.all(
                Validators.creditCard(),
                Validators.email()
        );

        et.addValidator(emailOrCreditValidator);

        // matching with other field
        et.matches(other, "Passwords don't match!");

        // or you can add any number of other fields
        et.matches(Arrays.asList(other, other, other), "Passwords don't match!");
    }
}
