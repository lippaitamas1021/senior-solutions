package employees;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<IsValidName, String> {

    private int minLength;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null &&
                value.length() > minLength &&
                Character.isUpperCase(value.charAt(0)) &&
                value.contains(" ");
    }

    @Override
    public void initialize(IsValidName constraintAnnotation) {
        minLength = constraintAnnotation.minLength();
    }
}
