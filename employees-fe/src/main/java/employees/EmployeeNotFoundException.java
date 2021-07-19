package employees;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import java.net.URI;

public class EmployeeNotFoundException extends AbstractThrowableProblem {

    public EmployeeNotFoundException() {
        super(URI.create("employees/not-found"),
                "Not found",
                Status.NOT_FOUND,
                "Employee not found"
                );
    }
}
