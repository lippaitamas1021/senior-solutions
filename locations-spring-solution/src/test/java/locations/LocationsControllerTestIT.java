package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LocationsControllerTestIT {

    @Autowired
    LocationsController locationsController;

    @Test
    void getLocationsTest() {
        assertEquals(3, locationsController.getLocations(Optional.empty()).size());
    }
}
