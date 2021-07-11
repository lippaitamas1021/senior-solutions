package locations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CoordinateValidator.class)
public @interface Coordinate {

    String message() default "Valid latitude must be between -90.0 & 90.0 and valid longitude must be between -180.0 & 180.0).";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

//    Type type() default Type.LAT;
}
