package nav;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsValidCreateAppointmentCommandValidator.class)
public @interface IsValidCreateAppointmentCommand {
    String message() default "Invalid date";
    Class<?>[] groups() default { };
    Class<? extends Payload> [] payload() default { };
}
