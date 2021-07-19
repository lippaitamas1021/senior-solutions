package nav;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsValidCreateAppointmentCommandValidator implements ConstraintValidator<IsValidCreateAppointmentCommand, CreateAppointmentCommand> {

    @Override
    public boolean isValid(CreateAppointmentCommand value, ConstraintValidatorContext context) {
        return false;
    }
}
