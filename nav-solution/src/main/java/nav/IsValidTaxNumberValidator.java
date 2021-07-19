package nav;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsValidTaxNumberValidator implements ConstraintValidator<IsValidTaxNumber, String> {

    TaxNumberValidator pojo = new TaxNumberValidator();

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return pojo.check(s);
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }
}
