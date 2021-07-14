package locations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<isValidName, String> {

    private int minLength;

    @Override
    public void initialize(isValidName constraintAnnotation) {
        minLength = constraintAnnotation.minLength();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null &&
                s.length() > minLength &&
                Character.isUpperCase(s.charAt(0));
    }
}
