package nav;

import lombok.AllArgsConstructor;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class IsValidTypeValidator implements ConstraintValidator<IsValidType, String> {

    private NavService navService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return navService.hasValidType(s);
    }
}
