package locations;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import java.net.URI;

public class LocationNotFoundException extends AbstractThrowableProblem {

    public LocationNotFoundException() {
        super(URI.create("locations/not found"),
                "Not found",
                Status.NOT_FOUND,
                "Location not found");
    }
}